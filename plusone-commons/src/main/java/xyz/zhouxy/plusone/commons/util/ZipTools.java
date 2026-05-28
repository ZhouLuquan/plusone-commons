/*
 * Copyright 2025-present ZhouXY
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package xyz.zhouxy.plusone.commons.util;

import static xyz.zhouxy.plusone.commons.util.AssertTools.checkArgument;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterOutputStream;

import javax.annotation.CheckForNull;
import javax.annotation.Nullable;

/**
 * zip 工具类
 *
 * <p>
 * 提供最基础的数据压缩/解压方法
 *
 * @author ZhouXY
 *
 * @see Deflater
 * @see Inflater
 */
public class ZipTools {

    /**
     * 使用默认压缩级别压缩数据
     *
     * @param input 输入
     * @return 压缩后的数据
     *
     * @throws IOException 发生 I/O 错误时抛出
     */
    public static byte[] zip(@Nullable byte[] input) throws IOException {
        return zipInternal(input, Deflater.DEFAULT_COMPRESSION);
    }

    /**
     * 使用指定压缩级别压缩数据
     *
     * @param input 输入
     * @param level 压缩级别
     * @return 压缩后的数据
     *
     * @throws IOException 发生 I/O 错误时抛出
     */
    public static byte[] zip(@Nullable byte[] input, int level) throws IOException {
        checkArgument((level >= 0 && level <= 9) || level == Deflater.DEFAULT_COMPRESSION,
                "invalid compression level");
        return zipInternal(input, level);
    }

    @CheckForNull
    private static byte[] zipInternal(@Nullable byte[] input, int level) throws IOException {
        if (input == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (DeflaterOutputStream dos = new DeflaterOutputStream(out, new Deflater(level))) {
            dos.write(input);
            dos.finish();
            return out.toByteArray();
        }
    }

    /**
     * 解压数据
     *
     * @param input 输入
     * @return 解压后的数据
     *
     * @throws IOException 发生 I/O 错误时抛出
     */
    @CheckForNull
    public static byte[] unzip(@Nullable byte[] input) throws IOException {
        if (input == null) {
            return null;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (InflaterOutputStream dos = new InflaterOutputStream(out, new Inflater())) {
            dos.write(input);
            dos.finish();
            return out.toByteArray();
        }
    }

    private ZipTools() {
        throw new IllegalStateException("Utility class");
    }
}
