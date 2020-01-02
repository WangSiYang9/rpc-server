package com.wsy.study;

import java.io.Serializable;

/**
 * @author 王思洋
 * @version 1.0
 * @date 2019-06-11 16:57
 */
public class RpcRequest implements Serializable {

    private Object [] patameters;

    private String className;

    private String methodName;

    private String version;


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object[] getPatameters() {
        return patameters;
    }

    public void setPatameters(Object[] patameters) {
        this.patameters = patameters;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }


}
