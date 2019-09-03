package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.oddys.timetracking.service.ServiceException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManager {
    private static final Logger log = LogManager.getLogger();
    private static TransactionManager INSTANCE = new TransactionManager();
    private final ConnectionWrapper CONNECTION_WRAPPER;

    private TransactionManager() {
        CONNECTION_WRAPPER = ConnectionWrapper.getInstance();
    }

    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    public <T> T getProxy(T object) {
        Class<?> targetClass = object.getClass();
        ClassLoader classLoader = targetClass.getClassLoader();
        Class<?>[] interfaces = targetClass.getInterfaces();
        @SuppressWarnings("unchecked")
        T proxy = (T) Proxy.newProxyInstance(classLoader, interfaces, getInvocationHandler(object));
        return proxy;
    }

    private InvocationHandler getInvocationHandler(Object target) {
        return new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                Object result = null;
                try (Connection connection = CONNECTION_WRAPPER.getConnection()) {
                    connection.setAutoCommit(false);
                    try {
                        result = method.invoke(target, args);
                    } catch (InvocationTargetException e) {
                        connection.rollback();
                        throw new ServiceException("Failed to perform a transaction", e.getCause());
                    }
                    connection.commit();
                } catch (SQLException | NullPointerException e) {
                    throw new ServiceException("Failed to perform a transaction", e);
                }
                return result;
            }
        };
    }
}
