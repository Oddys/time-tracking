package org.oddys.timetracking.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static final ConfigManager INSTANCE = new ConfigManager();
    public static final String DBMS = "db.dbms";
    public static final String HOME_PATH = "path.home";
    public static final String CABINET_PATH = "path.cabinet";
    public static final String USERS_LIST_PATH = "path.users.list";
    private static final String CONFIG_FILE_NAME = "config.properties";
    private static final String SQL_QUERIES_FILE_NAME = "db/sql_queries.properties";
    private final Properties PROPERTIES;

    private ConfigManager() {
        Properties properties = new Properties();
        ClassLoader classLoader = ConfigManager.class.getClassLoader();
        try (InputStream config = classLoader.getResourceAsStream(CONFIG_FILE_NAME);
             InputStream sqlQueries = classLoader.getResourceAsStream(SQL_QUERIES_FILE_NAME)) {
            properties.load(config);
            properties.load(sqlQueries);
        } catch (IOException | NullPointerException | IllegalArgumentException e) {
            throw new ResourceInitializationException("Failed to obtain properties from a file", e);
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
