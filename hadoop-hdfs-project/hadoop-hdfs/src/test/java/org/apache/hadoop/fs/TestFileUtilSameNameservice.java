/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.fs;

import java.io.File;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hdfs.DFSTestUtil;
import org.apache.hadoop.hdfs.HdfsConfiguration;
import org.apache.hadoop.hdfs.MiniDFSCluster;
import org.apache.hadoop.hdfs.MiniDFSNNTopology;
import org.apache.hadoop.test.GenericTestUtils;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests copying between independent clusters with the same logical URI.
 */
public class TestFileUtilSameNameservice {
  private static final String NAMESERVICE = "nn";
  private static final URI NAMESERVICE_URI =
      URI.create("hdfs://" + NAMESERVICE);
  private static final Path FILE =
      new Path(NAMESERVICE_URI + "/hive/warehouse/db/tbl/file.txt");

  @Test(timeout = 60000)
  public void testCopyBetweenClustersWithSameNameservice() throws Exception {
    Configuration sourceConf = new HdfsConfiguration();
    Configuration destinationConf = new HdfsConfiguration();
    File testDir = GenericTestUtils.getRandomizedTestDir();
    File sourceDir = new File(testDir, "source");
    File destinationDir = new File(testDir, "destination");
    MiniDFSNNTopology sourceTopology =
        MiniDFSNNTopology.simpleFederatedTopology(NAMESERVICE);
    MiniDFSNNTopology destinationTopology =
        MiniDFSNNTopology.simpleFederatedTopology(NAMESERVICE);

    try (MiniDFSCluster sourceCluster =
             new MiniDFSCluster.Builder(sourceConf, sourceDir)
                 .nnTopology(sourceTopology)
                 .numDataNodes(1)
                 .build();
         MiniDFSCluster destinationCluster =
             new MiniDFSCluster.Builder(destinationConf, destinationDir)
                 .nnTopology(destinationTopology)
                 .numDataNodes(1)
                 .build();
         FileSystem sourceFs =
             FileSystem.newInstance(NAMESERVICE_URI, sourceConf);
         FileSystem destinationFs =
             FileSystem.newInstance(NAMESERVICE_URI, destinationConf)) {
      sourceCluster.waitActive();
      destinationCluster.waitActive();

      String contents = "copied between clusters";
      DFSTestUtil.writeFile(sourceFs, FILE, contents);
      assertFalse(destinationFs.exists(FILE));

      assertTrue(FileUtil.copy(sourceFs, FILE, destinationFs, FILE,
          false, destinationConf));

      assertTrue(destinationFs.exists(FILE));
      assertEquals(contents, DFSTestUtil.readFile(destinationFs, FILE));
      assertTrue(sourceFs.exists(FILE));
    } finally {
      FileUtil.fullyDelete(testDir);
    }
  }
}
