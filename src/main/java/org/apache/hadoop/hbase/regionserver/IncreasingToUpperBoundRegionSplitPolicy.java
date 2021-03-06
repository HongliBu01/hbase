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
package org.apache.hadoop.hbase.regionserver;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.util.Bytes;

/**
 * Split size is the number of regions that are on this server that all are
 * of the same table, squared, times the region flush size OR the maximum
 * region split size, whichever is smaller.  For example, if the flush size
 * is 128M, then on first flush we will split which will make two regions
 * that will split when their size is 2 * 2 * 128M = 512M.  If one of these
 * regions splits, then there are three regions and now the split size is
 * 3 * 3 * 128M =  1152M, and so on until we reach the configured
 * maximum filesize and then from there on out, we'll use that.
 */
public class IncreasingToUpperBoundRegionSplitPolicy
extends ConstantSizeRegionSplitPolicy {
  static final Log LOG =
    LogFactory.getLog(IncreasingToUpperBoundRegionSplitPolicy.class);
  private long flushSize;

  @Override
  protected void configureForRegion(HRegion region) {
    super.configureForRegion(region);
    Configuration conf = getConf();
    HTableDescriptor desc = region.getTableDesc();
    if (desc != null) {
      this.flushSize = desc.getMemStoreFlushSize();
    }
    if (this.flushSize <= 0) {
      this.flushSize = conf.getLong(HConstants.HREGION_MEMSTORE_FLUSH_SIZE,
        HTableDescriptor.DEFAULT_MEMSTORE_FLUSH_SIZE);
    }
  }

  @Override
  protected boolean shouldSplit() {
    if (region.shouldForceSplit()) return true;
    boolean foundABigStore = false;
    // Get count of regions that have the same common table as this.region
    int tableRegionsCount = getCountOfCommonTableRegions();
    // Get size to check
    long sizeToCheck = getSizeToCheck(tableRegionsCount);

    for (Store store : region.getStores().values()) {
      // If any of the stores is unable to split (eg they contain reference files)
      // then don't split
      if ((!store.canSplit())) {
        return false;
      }

      // Mark if any store is big enough
      long size = store.getSize();
      if (size > sizeToCheck) {
        LOG.debug("ShouldSplit because " + store.getColumnFamilyName() +
          " size=" + size + ", sizeToCheck=" + sizeToCheck +
          ", regionsWithCommonTable=" + tableRegionsCount);
        foundABigStore = true;
      }
    }

    return foundABigStore;
  }

  /**
   * @return Region max size or <code>count of regions squared * flushsize, which ever is
   * smaller; guard against there being zero regions on this server.
   */
  long getSizeToCheck(final int tableRegionsCount) {
    return tableRegionsCount == 0? getDesiredMaxFileSize():
      Math.min(getDesiredMaxFileSize(),
        this.flushSize * (tableRegionsCount * tableRegionsCount));
  }

  /**
   * @return Count of regions on this server that share the table this.region
   * belongs to
   */
  private int getCountOfCommonTableRegions() {
    RegionServerServices rss = this.region.getRegionServerServices();
    // Can be null in tests
    if (rss == null) return 0;
    byte [] tablename = this.region.getTableDesc().getName();
    int tableRegionsCount = 0;
    try {
      List<HRegion> hri = rss.getOnlineRegions(tablename);
      tableRegionsCount = hri == null || hri.isEmpty()? 0: hri.size();
    } catch (IOException e) {
      LOG.debug("Failed getOnlineRegions " + Bytes.toString(tablename), e);
    }
    return tableRegionsCount;
  }
}
