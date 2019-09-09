package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.entity.User;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.ArrayList;
import java.util.List;

public class FindUserServiceImpl implements FindUserService {
    private static final FindUserServiceImpl INSTANCE = new FindUserServiceImpl();
    private UserDao userDao;

    public static FindUserServiceImpl getInstance() {
        return INSTANCE;
    }

    private FindUserServiceImpl() {
        String dbmsName = ConfigManager.getInstance().getProperty(ConfigManager.DBMS);
        userDao = DaoFactoryProvider.getInstance().getFactory(dbmsName).getUserDao();
    }

    @Override
    public List<UserDto> search(String lastName) throws ServiceException {
        List<UserDto> userDTOs = new ArrayList<>();
        try {
            List<User> users = userDao.findByLastName(lastName);
            if (!users.isEmpty()) {
                for (User user: users) {
                    userDTOs.add(ModelMapperWrapper.getMapper().map(user, UserDto.class));
                }
            }
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return userDTOs;
    }
}
