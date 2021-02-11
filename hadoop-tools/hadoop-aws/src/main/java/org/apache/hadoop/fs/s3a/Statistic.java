/*
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

package org.apache.hadoop.fs.s3a;

import org.apache.hadoop.fs.StorageStatistics.CommonStatisticNames;

import java.util.HashMap;
import java.util.Map;

/**
 * Statistic which are collected in S3A.
 * These statistics are available at a low level in {@link S3AStorageStatistics}
 * and as metrics in {@link S3AInstrumentation}
 */
public enum Statistic {

  DIRECTORIES_CREATED("directories_created",
      "Total number of directories created through the object store."),
  DIRECTORIES_DELETED("directories_deleted",
      "Total number of directories deleted through the object store."),
  FILES_COPIED("files_copied",
      "Total number of files copied within the object store."),
  FILES_COPIED_BYTES("files_copied_bytes",
      "Total number of bytes copied within the object store."),
  FILES_CREATED("files_created",
      "Total number of files created through the object store."),
  FILES_DELETED("files_deleted",
      "Total number of files deleted from the object store."),
  FAKE_DIRECTORIES_CREATED("fake_directories_created",
      "Total number of fake directory entries created in the object store."),
  FAKE_DIRECTORIES_DELETED("fake_directories_deleted",
      "Total number of fake directory deletes submitted to object store.",
      TYPE_COUNTER),
  IGNORED_ERRORS("ignored_errors", "Errors caught and ignored",
      TYPE_COUNTER),

  INVOCATION_ABORT(
      StoreStatisticNames.OP_ABORT,
      "Calls of abort()",
      TYPE_DURATION),
  INVOCATION_COPY_FROM_LOCAL_FILE(
      StoreStatisticNames.OP_COPY_FROM_LOCAL_FILE,
      "Calls of copyFromLocalFile()",
      TYPE_COUNTER),
  INVOCATION_CREATE(
      StoreStatisticNames.OP_CREATE,
      "Calls of create()",
      TYPE_COUNTER),
  INVOCATION_CREATE_NON_RECURSIVE(
      StoreStatisticNames.OP_CREATE_NON_RECURSIVE,
      "Calls of createNonRecursive()",
      TYPE_COUNTER),
  INVOCATION_DELETE(
      StoreStatisticNames.OP_DELETE,
      "Calls of delete()",
      TYPE_COUNTER),
  INVOCATION_EXISTS(
      StoreStatisticNames.OP_EXISTS,
      "Calls of exists()",
      TYPE_COUNTER),
  INVOCATION_GET_DELEGATION_TOKEN(
      StoreStatisticNames.OP_GET_DELEGATION_TOKEN,
      "Calls of getDelegationToken()",
      TYPE_COUNTER),
  INVOCATION_GET_FILE_CHECKSUM(
      StoreStatisticNames.OP_GET_FILE_CHECKSUM,
      "Calls of getFileChecksum()",
      TYPE_COUNTER),
  INVOCATION_GET_FILE_STATUS(
      StoreStatisticNames.OP_GET_FILE_STATUS,
      "Calls of getFileStatus()",
      TYPE_COUNTER),
  INVOCATION_GLOB_STATUS(
      StoreStatisticNames.OP_GLOB_STATUS,
      "Calls of globStatus()",
      TYPE_COUNTER),
  INVOCATION_IS_DIRECTORY(
      StoreStatisticNames.OP_IS_DIRECTORY,
      "Calls of isDirectory()",
      TYPE_COUNTER),
  INVOCATION_IS_FILE(
      StoreStatisticNames.OP_IS_FILE,
      "Calls of isFile()",
      TYPE_COUNTER),
  INVOCATION_LIST_FILES(
      StoreStatisticNames.OP_LIST_FILES,
      "Calls of listFiles()",
      TYPE_COUNTER),
  INVOCATION_LIST_LOCATED_STATUS(
      StoreStatisticNames.OP_LIST_LOCATED_STATUS,
      "Calls of listLocatedStatus()",
      TYPE_COUNTER),
  INVOCATION_LIST_STATUS(
      StoreStatisticNames.OP_LIST_STATUS,
      "Calls of listStatus()",
      TYPE_COUNTER),
  INVOCATION_MKDIRS(
      StoreStatisticNames.OP_MKDIRS,
      "Calls of mkdirs()",
      TYPE_COUNTER),
  INVOCATION_OPEN(
      StoreStatisticNames.OP_OPEN,
      "Calls of open()",
      TYPE_COUNTER),
  INVOCATION_RENAME(
      StoreStatisticNames.OP_RENAME,
      "Calls of rename()",
      TYPE_COUNTER),

  /* The XAttr API metrics are all durations */
  INVOCATION_XATTR_GET_MAP(
      StoreStatisticNames.OP_XATTR_GET_MAP,
      "Calls of getXAttrs(Path path)",
      TYPE_DURATION),
  INVOCATION_XATTR_GET_NAMED(
      StoreStatisticNames.OP_XATTR_GET_NAMED,
      "Calls of getXAttr(Path, String)",
      TYPE_DURATION),
  INVOCATION_XATTR_GET_NAMED_MAP(
      StoreStatisticNames.OP_XATTR_GET_NAMED_MAP,
      "Calls of xattr()",
      TYPE_DURATION),
  INVOCATION_OP_XATTR_LIST(
      StoreStatisticNames.OP_XATTR_LIST,
      "Calls of getXAttrs(Path path, List<String> names)",
      TYPE_DURATION),

  STREAM_WRITE_BLOCK_UPLOADS_PENDING("stream_write_block_uploads_pending",
      "Gauge of block/partitions uploads queued to be written"),
  STREAM_WRITE_BLOCK_UPLOADS_DATA_PENDING(
      "stream_write_block_uploads_data_pending",
      "Gauge of block/partitions data uploads queued to be written"),
  STREAM_WRITE_TOTAL_TIME("stream_write_total_time",
      "Count of total time taken for uploads to complete"),
  STREAM_WRITE_TOTAL_DATA("stream_write_total_data",
      "Count of total data uploaded in block output"),
  STREAM_WRITE_QUEUE_DURATION("stream_write_queue_duration",
      "Total queue duration of all block uploads"),

  // S3guard committer stats
  COMMITTER_COMMITS_CREATED(
      "committer_commits_created",
      "Number of files to commit created"),
  COMMITTER_COMMITS_COMPLETED(
      "committer_commits_completed",
      "Number of files committed"),
  COMMITTER_JOBS_SUCCEEDED(
      "committer_jobs_completed",
      "Number of successful jobs"),
  COMMITTER_JOBS_FAILED(
      "committer_jobs_failed",
      "Number of failed jobs"),
  COMMITTER_TASKS_SUCCEEDED(
      "committer_tasks_completed",
      "Number of successful tasks"),
  COMMITTER_TASKS_FAILED(
      "committer_tasks_failed",
      "Number of failed tasks"),
  COMMITTER_BYTES_COMMITTED(
      "committer_bytes_committed",
      "Amount of data committed"),
  COMMITTER_BYTES_UPLOADED(
      "committer_bytes_uploaded",
      "Number of bytes uploaded duing commit operations"),
  COMMITTER_COMMITS_FAILED(
      "committer_commits_failed",
      "Number of commits failed"),
  COMMITTER_COMMITS_ABORTED(
      "committer_commits_aborted",
      "Number of commits aborted"),
  COMMITTER_COMMITS_REVERTED(
      "committer_commits_reverted",
      "Number of commits reverted"),
  COMMITTER_MAGIC_FILES_CREATED(
      "committer_magic_files_created",
      "Number of files created under 'magic' paths"),

  // S3guard stats
  S3GUARD_METADATASTORE_PUT_PATH_REQUEST(
      "s3guard_metadatastore_put_path_request",
      "S3Guard metadata store put one metadata path request"),
  S3GUARD_METADATASTORE_PUT_PATH_LATENCY(
      "s3guard_metadatastore_put_path_latency",
      "S3Guard metadata store put one metadata path latency"),
  S3GUARD_METADATASTORE_INITIALIZATION("s3guard_metadatastore_initialization",
      "S3Guard metadata store initialization times"),
  S3GUARD_METADATASTORE_RETRY("s3guard_metadatastore_retry",
      "S3Guard metadata store retry events"),
  S3GUARD_METADATASTORE_THROTTLED("s3guard_metadatastore_throttled",
      "S3Guard metadata store throttled events"),
  S3GUARD_METADATASTORE_THROTTLE_RATE(
      "s3guard_metadatastore_throttle_rate",
      "S3Guard metadata store throttle rate"),

  STORE_IO_THROTTLED("store_io_throttled", "Requests throttled and retried");

  private static final Map<String, Statistic> SYMBOL_MAP =
      new HashMap<>(Statistic.values().length);
  static {
    for (Statistic stat : values()) {
      SYMBOL_MAP.put(stat.getSymbol(), stat);
    }
  }

  Statistic(String symbol, String description) {
    this.symbol = symbol;
    this.description = description;
  }

  private final String symbol;
  private final String description;

  public String getSymbol() {
    return symbol;
  }

  /**
   * Get a statistic from a symbol.
   * @param symbol statistic to look up
   * @return the value or null.
   */
  public static Statistic fromSymbol(String symbol) {
    return SYMBOL_MAP.get(symbol);
  }

  public String getDescription() {
    return description;
  }

  /**
   * The string value is simply the symbol.
   * This makes this operation very low cost.
   * @return the symbol of this statistic.
   */
  @Override
  public String toString() {
    return symbol;
  }
}
