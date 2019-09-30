package org.oddys.timetracking.transaction;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;

public class TransactionProxy {
    private static final Logger LOGGER = LogManager.getLogger();
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
            try {
                Object result = null;
                transactionManager.beginTransaction();
                try {
                    result = method.invoke(target, args);
                } catch (IllegalAccessException | InvocationTargetException e) {
                    transactionManager.rollback();
                    LOGGER.error("TransactionProxy failed to invoke a target method",
                            e.getCause());
                    throw new ProxyException(e.getCause());
                }
                transactionManager.commit();
                return result;
            } finally {
                transactionManager.endTransaction();
            }
        };
    }
}
