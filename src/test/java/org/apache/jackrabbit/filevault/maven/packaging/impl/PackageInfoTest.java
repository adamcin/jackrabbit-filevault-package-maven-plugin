/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.jackrabbit.filevault.maven.packaging.impl;

import java.io.File;

import org.apache.jackrabbit.vault.packaging.PackageType;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class PackageInfoTest {

    private static final String RESOURCE_DIR = "/test-packages/";

    private File load(String path) {
        return new File(getClass().getResource(RESOURCE_DIR + path).getFile());
    }

    @Test
    public void test_pkg_with_filter_and_manifest() throws Exception {
        PackageInfo info = PackageInfo.read(load("pkg-with-filter-and-manifest.zip"));
        assertNotNull("PackageInfo", info);
        assertEquals("PackageId", "com.day.jcr.vault.unit:vault-plugin-test-pkg:1.0.0-SNAPSHOT", info.getId().toString());
        assertEquals("filter size", 1, info.getFilter().getFilterSets().size());
        assertTrue("contains filter", info.getFilter().contains("/libs/granite/replication"));
        assertEquals("package type", PackageType.APPLICATION, info.getPackageType());
    }

    @Test
    public void test_pkg_with_no_manifest() throws Exception {
        PackageInfo info = PackageInfo.read(load("pkg-with-no-manifest.zip"));
        assertNotNull("PackageInfo", info);
        assertEquals("PackageId", "com.day.jcr.vault.unit:vault-plugin-test-pkg:1.0.0-SNAPSHOT", info.getId().toString());
        assertEquals("filter size", 1, info.getFilter().getFilterSets().size());
        assertTrue("contains filter", info.getFilter().contains("/libs/granite/replication"));
        assertEquals("package type", PackageType.MIXED, info.getPackageType());
    }

    @Test
    public void test_non_valid_package() throws Exception {
        PackageInfo info = PackageInfo.read(load("non-valid-package.zip"));
        assertNull("PackageInfo", info);
    }
}
