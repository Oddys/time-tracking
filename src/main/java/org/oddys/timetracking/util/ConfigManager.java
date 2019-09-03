package org.oddys.timetracking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final ConfigManager INSTANCE = new ConfigManager();
    public static final String DBMS = "db.dbms";
    public static final String HOME_PATH = "path.home";
    public static final String CABINET_PATH = "path.cabinet";
    private static final String CONFIG_FILE_NAME = "config.properties";
    private final Properties PROPERTIES;

    private ConfigManager() {
        InputStream inputStream = ConfigManager.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE_NAME);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            throw new ResourceInitializationException("Failed to obtain configuration properties from a file", e);
        }
        PROPERTIES = properties;
    }

    public static ConfigManager getInstance() {
        return INSTANCE;
    }

    public String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
