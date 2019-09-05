package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.sql.SQLException;

public class TransactionProxy {
    private static final Logger log = LogManager.getLogger();
    private static TransactionProxy INSTANCE = new TransactionProxy();
    private TransactionManager transactionManager = TransactionManager.getInstance();

    private TransactionProxy() {}

    public static TransactionProxy getInstance() {
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
        return (proxy, method, args) -> {
            Object result = null;
            try {
                transactionManager.beginTransaction();
                try {
                    result = method.invoke(target, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    transactionManager.rollback();
                    throw new ProxyException("Transaction proxy failed to invoke a target method",
                            e.getCause());
                }
                transactionManager.commit();
            } catch (SQLException | ProxyException e) {
                log.error("Transaction proxy failed to perform a transaction", e);
            } finally {
                transactionManager.endTransaction();
            }
            return result;
        };
    }
}
