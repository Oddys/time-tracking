//package org.oddys.timetracking.service;
//
//import org.oddys.timetracking.connection.ConnectionPool;
//import org.oddys.timetracking.dao.DaoFactoryProvider;
//import org.oddys.timetracking.dao.RoleDao;
//import org.oddys.timetracking.dao.UserDao;
//import org.oddys.timetracking.dto.UserDto;
//import org.oddys.timetracking.entity.Role;
//import org.oddys.timetracking.entity.User;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.Optional;
//
//public class UserService {
//    private static final UserService INSTANCE = new UserService();
//
//    private UserService() {}
//
//    public static UserService getInstance() {
//        return INSTANCE;
//    }
//
//    public UserDto getUserByLogin(String login) {
//        try {
//            Connection connection = ConnectionPool.getInstance().getConnection();
//            connection.setAutoCommit(false);
//            UserDao userDao = DaoFactoryProvider.getInstance().getFactory().getUserDao(connection);
//            Optional<User> userOpt = userDao.findByLogin(login);
//            RoleDao roleDao = DaoFactoryProvider.getInstance().getFactory().getRoleDao(connection);
//            Optional<Role> role = roleDao.findById(userOpt.get().getRoleId());
//            connection.commit();
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace(); // FIXME Propagate exception
//        }
//    }
//}
