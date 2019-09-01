package org.oddys.timetracking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConfigProvider {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config"); // TODO Check if it's appropriate
    private final Properties PROPERTIES;

    private ConfigProvider(Properties properties) {
        PROPERTIES = properties;
    }

    private static class InitializationHelper {
        private static final ConfigProvider PROVIDER;
        private static final String CONFIG_FILE_NAME = "config.properties";

        static {
            InputStream inputStream = InitializationHelper.class
                    .getClassLoader()
                    .getResourceAsStream(CONFIG_FILE_NAME);
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new ResourceInitializationException(e);
            }
            PROVIDER = new ConfigProvider(properties);
        }
    }

    public static ConfigProvider getInstance() {
        return InitializationHelper.PROVIDER;
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
