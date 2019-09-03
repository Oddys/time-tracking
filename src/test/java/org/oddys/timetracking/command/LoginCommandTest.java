package org.oddys.timetracking.command;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LoginCommandTest {
    @Mock
    LoginService loginService;

    @Mock
    UserDto userDto;

    @Mock
    HttpServletRequest req;

    @Mock
    HttpSession session;

    @InjectMocks
    LoginCommand loginCommand = LoginCommand.getInstance();

    @Test
    public void returnCabinetPageIfValidCredentials() {
        when(loginService.logIn(any(String.class), any(char[].class))).thenReturn(userDto);
        when(req.getParameter("login")).thenReturn("login");
        when(req.getParameter("password")).thenReturn("password");
        when(req.getSession()).thenReturn(session);
        assertEquals("/WEB-INF/pages/cabinet.jsp", loginCommand.execute(req));
    }
}
