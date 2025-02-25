/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */
package org.apache.hc.core5.http.nio.entity;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

import org.junit.Assert;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

/**
 * @since 5.0
 */
public class TestPathEntityProducer {

    @ClassRule
    public static final TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void testFileLengthMaxIntPlusOne() throws IOException {
        final File file = tempFolder.newFile("test.bin");
        final Path path = file.toPath();
        try (RandomAccessFile raFile = new RandomAccessFile(file, "rw")) {
            final long expectedLength = 1L + Integer.MAX_VALUE;
            raFile.setLength(expectedLength);
            final PathEntityProducer fileEntityProducer = new PathEntityProducer(path, StandardOpenOption.READ);
            Assert.assertEquals(expectedLength, fileEntityProducer.getContentLength());
        }
    }

}
