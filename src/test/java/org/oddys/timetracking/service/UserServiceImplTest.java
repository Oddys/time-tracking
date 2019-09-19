package org.oddys.timetracking.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {
    private final String LOGIN = "login";
    private final char[] CORRECT_PASSWORD = "password".toCharArray();
    private final User USER = new User(7L, LOGIN, DigestUtils.sha256Hex(String.valueOf(CORRECT_PASSWORD)),
            "first name", "last name", 7L, "role");

    @Mock
    private UserDao userDao;

    @InjectMocks
    private final UserService service = UserServiceImpl.getInstance();

    @Test
    public void returnUserDtoIfValidCredentials() throws DaoException, ServiceException {
        UserDto userDto = new UserDto(USER);
        when(userDao.findByLogin(LOGIN)).thenReturn(USER);
        assertEquals(userDto, service.signIn(LOGIN, CORRECT_PASSWORD));
    }

    @Test
    public void returnNullIfNonValidCredentials() throws DaoException, ServiceException {
        char[] incorrectPassword = "not a password".toCharArray();
        when(userDao.findByLogin(LOGIN)).thenReturn(USER);
        assertNull(service.signIn(LOGIN, incorrectPassword));
    }

    @Test
    public void returnNullIfNonExistingLogin() throws DaoException, ServiceException {
        String misspelledLogin = "oglin";
        when(userDao.findByLogin(misspelledLogin)).thenReturn(null);
        assertNull(service.signIn(misspelledLogin, CORRECT_PASSWORD));
    }
}
