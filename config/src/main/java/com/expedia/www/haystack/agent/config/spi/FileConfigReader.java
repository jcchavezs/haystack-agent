/*
 *  Copyright 2017 Expedia, Inc.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 *
 */

package com.expedia.www.haystack.agent.config.spi;

import com.expedia.www.haystack.agent.config.Config;
import com.expedia.www.haystack.agent.config.ConfigReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.commons.lang3.StringUtils;

public class FileConfigReader implements ConfigReader {
    private final static String DEFAULT_CONFIG_FILE_PATH = "agent-config.yaml";

    @Override
    public String getName() {
        return "file";
    }

    @Override
    public Config read() throws Exception {
        final ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        // fallback to the default config path
        String configFilePath = System.getenv("HAYSTACK_CONFIG_FILE_PATH");
        if(StringUtils.isEmpty(configFilePath)) {
            configFilePath = System.getProperty("HAYSTACK_CONFIG_FILE_PATH", DEFAULT_CONFIG_FILE_PATH);
        }

        return mapper.readValue(configFilePath, Config.class);
    }
}
