package org.oddys.timetracking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    public static final String DBMS = "db.dbms";
    public static final String HOME_PATH = "path.home";
    public static final String CABINET_PATH = "path.cabinet";
    private final Properties PROPERTIES;

    private ConfigManager(Properties properties) {
        PROPERTIES = properties;
    }

    private static class InitializationHelper {
        private static final ConfigManager PROVIDER;
        private static final String CONFIG_FILE_NAME = "config.properties";

        static {
            InputStream inputStream = InitializationHelper.class.getClassLoader()
                    .getResourceAsStream(CONFIG_FILE_NAME);
            Properties properties = new Properties();
            try {
                properties.load(inputStream);
            } catch (IOException e) {
                throw new ResourceInitializationException(e);
            }
            PROVIDER = new ConfigManager(properties);
        }
    }

    public static ConfigManager getInstance() {
        return InitializationHelper.PROVIDER;
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
