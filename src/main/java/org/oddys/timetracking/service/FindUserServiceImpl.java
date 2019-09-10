package org.oddys.timetracking.service;

import org.oddys.timetracking.dao.DaoFactoryProvider;
import org.oddys.timetracking.dao.UserDao;
import org.oddys.timetracking.dao.mysql.DaoException;
import org.oddys.timetracking.dto.UserDto;
import org.oddys.timetracking.util.ConfigManager;
import org.oddys.timetracking.util.ModelMapperWrapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        try {
            return userDao.findByLastName(lastName).stream()
                    .map(u -> ModelMapperWrapper.getMapper().map(u, UserDto.class))
                    .collect(Collectors.toCollection(ArrayList::new));
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
