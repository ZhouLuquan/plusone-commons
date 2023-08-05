/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package xyz.zhouxy.plusone.commons.base;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

/**
 * JRE version
 */
public class JRE {

    private static final int JAVA_8 = 8;

    public static final int CURRENT_VERSION = getJre();

    public static boolean isJava8() {
        return CURRENT_VERSION == JAVA_8;
    }

    private static int getJre() {
        String version = System.getProperty("java.version");
        boolean isBlank = StringUtils.isBlank(version);
        if (!isBlank && version.startsWith("1.8")) {
            return JAVA_8;
        }
        // if JRE version is 9 or above, we can get version from
        // java.lang.Runtime.version()
        try {
            Object javaRunTimeVersion = MethodUtils.invokeMethod(Runtime.getRuntime(), "version");
            return (int) MethodUtils.invokeMethod(javaRunTimeVersion, "major");
        } catch (Exception e) {
            // Can't determine current JRE version (maybe java.version is null),
            // assuming that JRE version is 8.
        }
        // default java 8
        return JAVA_8;
    }

    private JRE() {
        throw new IllegalStateException("Utility class");
    }
}
