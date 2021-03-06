package org.oddys.timetracking.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignInCommandTest {
    @Mock
    private UserService service;

    @Mock
    private UserDto userDto;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpSession session;

    @InjectMocks
    private final Command signInCommand = SignInCommand.getInstance();

    @Before
    public void setUp() {
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("password")).thenReturn("password");
        when(request.getSession()).thenReturn(session);
    }

    @Test
    public void setUserAttrInSessionAndReturnCabinetPageIfValidCredentials() {
        when(service.signIn(any(String.class), any(char[].class))).thenReturn(userDto);
        String page = signInCommand.execute(request);
        verify(session).setAttribute("user", userDto);
        assertEquals("redirect:/time-tracking/cabinet", page);
    }

    @Test
    public void setErrorMessageKeyAttrInSessionAndReturnHomePageIfNonValidCredentials() {
        when(service.signIn(any(String.class), any(char[].class))).thenReturn(null);
        signInCommand.execute(request);
        verify(session).setAttribute("messageKey", "auth.error.notfound");
    }
}
