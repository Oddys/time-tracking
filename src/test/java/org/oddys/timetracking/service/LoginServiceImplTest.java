package org.oddys.timetracking.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.User;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginServiceImplTest {
    private final String LOGIN = "login";
    private final char[] CORRECT_PASSWORD = "password".toCharArray();
    private final char[] INCORRECT_PASSWORD = "not a password".toCharArray();
    private final User USER = new User(7L, LOGIN, CORRECT_PASSWORD,
            "first name", "last name", 7L, "role");
    private final UserDto USER_DTO = new UserDto(USER);

    @Mock
    private UserDao userDao;

    @InjectMocks
    private final LoginService service = LoginServiceImpl.getInstance();

    @Test
    public void returnUserDtoIfValidCredentials() {
        when(userDao.findByLogin(LOGIN)).thenReturn(USER);
        assertEquals(USER_DTO, service.logIn(LOGIN, CORRECT_PASSWORD));
    }
}
