package org.oddys.timetracking.command;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.service.ServiceException;
import org.oddys.timetracking.service.UserService;
import org.oddys.timetracking.util.ConfigManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SignInCommandTest {
    private final String CABINET_PAGE_URL = ConfigManager.getInstance()
            .getProperty("path.cabinet");
    private final String HOME_PAGE_URL = ConfigManager.getInstance()
            .getProperty("path.home");

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
    public void setUserAttrInSessionAndReturnCabinetPageIfValidCredentials() throws ServiceException {
        when(service.signIn(any(String.class), any(char[].class))).thenReturn(userDto);
        String page = signInCommand.execute(request);
        verify(session).setAttribute("user", userDto);
        assertEquals(CABINET_PAGE_URL, page);
    }

    @Test
    public void setErrorMessageKeyAttrInSessionAndReturnHomePageIfNonValidCredentials() throws ServiceException {
        when(service.signIn(any(String.class), any(char[].class))).thenReturn(null);
        String page = signInCommand.execute(request);
        verify(request).setAttribute("errorMessageKey", "auth.error.notfound");
        assertEquals(HOME_PAGE_URL, page);
    }
}
