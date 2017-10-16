package cn.coolcc;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * rpc请求
 * Created by chencheng on 17/9/20.
 */
public class Request {
    private static final AtomicInteger REQ_ID = new AtomicInteger();
    private Integer id;
    private String className;
    private String method;
    private Class<?>[] paramTypes;
    private Object[] params;

    public Request() {
    }

    public Request(String className, String method, Class<?>[] paramTypes, Object[] params) {
        this.id = REQ_ID.getAndIncrement();
        this.className = className;
        this.method = method;
        this.paramTypes = paramTypes;
        this.params = params;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Class<?>[] getParamTypes() {
        return paramTypes;
    }

    public void setParamTypes(Class<?>[] paramTypes) {
        this.paramTypes = paramTypes;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }
}
