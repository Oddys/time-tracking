package org.oddys.timetracking.util;

import org.apache.commons.codec.digest.DigestUtils;
import org.oddys.timetracking.entity.User;

import java.util.Arrays;
import java.util.Objects;

public class PasswordManager {
    private static final PasswordManager INSTANCE = new PasswordManager();

    private PasswordManager() {}

    public static PasswordManager getInstance() {
        return INSTANCE;
    }

    public String hashPassword(String password) {
        return DigestUtils.sha256Hex(password);
    }

    public void invalidate(char[] password) {
        Arrays.fill(password, Character.MIN_VALUE);
    }

    public boolean checkCredentials(String login, char[] password, User user) {
        return user != null && Objects.equals(login, user.getLogin())
                && user.getPassword().equals(hashPassword(String.valueOf(password)));
    }
}
