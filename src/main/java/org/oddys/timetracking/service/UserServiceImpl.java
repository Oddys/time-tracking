package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;
import org.oddys.timetracking.util.PasswordManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {
    private static final UserServiceImpl INSTANCE = new UserServiceImpl();
    private UserDao dao;

    public static UserServiceImpl getInstance() {
        return INSTANCE;
    }

    private UserServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        dao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserDao();
    }

    @Override
    public List<UserDto> search(String lastName) throws ServiceException {
        try {
            return dao.findByLastName(lastName).stream()
                    .map(u -> ModelMapperWrapper.getMapper().map(u, UserDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean addUser(User user) throws ServiceException {
        try {
            return dao.findByLogin(user.getLogin()) == null && dao.add(user);
        } catch (DaoException e) {
            throw new ServiceException("Failed to add User", e);
        }
    }

    @Override
    public UserDto signIn(String login, char[] password) throws ServiceException {
        UserDto userDto = null;
        try {
            User user = dao.findByLogin(login);
            if (PasswordManager.getInstance().checkCredentials(login, password, user)) {
                userDto = ModelMapperWrapper.getMapper().map(user, UserDto.class);
            }
            PasswordManager.getInstance().invalidate(password);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return userDto;
    }
}
