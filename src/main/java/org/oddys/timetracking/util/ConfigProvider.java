package org.oddys.timetracking.util;

import java.util.ResourceBundle;

public class ConfigProvider {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config");

    private ConfigProvider() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
