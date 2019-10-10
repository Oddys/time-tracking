package org.oddys.timetracking.service;

import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.User;

import java.util.List;

/**
 * This interface provides functionality related to Users.
 */
public interface UserService {
    /**
     * Retries a list of user with a given last name.
     *
     * @param lastName a last name to be searched
     * @return a list of UserDto objects
     */
    List<UserDto> search(String lastName);

    /**
     * Attempts to store a new User.
     *
     * @param user a User object containing the data to be stored
     * @return true if the User was stored successfully, false if such a User already exists
     */
    boolean addUser(User user);

    /**
     * Performs the authentication using given credentials.
     *
     * @param login a user login
     * @param password a user password
     * @return a UserDto containing the just authenticated user data
     * if the authentication was successful, null otherwise
     */
    UserDto signIn(String login, char[] password);
}
