package org.oddys.timetracking.util;

import java.util.ResourceBundle;

public class ConfigProvider {
    private static final ResourceBundle resourceBundle = ResourceBundle.getBundle("config"); // TODO Check if it's appropriate

    private ConfigProvider() {}

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
