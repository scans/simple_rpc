package cn.coolcc.proxy;

import cn.coolcc.RpcClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by chencheng on 17/9/20.
 */
public class Proxy<T> implements InvocationHandler {

    private Class<T> clazz;

    public Proxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    @SuppressWarnings("all")
    public T getInstance() {
        return (T) java.lang.reflect.Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{clazz}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        RpcClient.call(clazz, method, args);
        return null;
    }
}
