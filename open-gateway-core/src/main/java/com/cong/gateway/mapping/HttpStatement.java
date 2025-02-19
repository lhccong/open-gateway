package com.cong.gateway.mapping;

/**
 * http 语句
 *
 * @author cong
 * @date 2025/02/19
 */
public class HttpStatement {

    /** 应用名称； */
    private final String application;
    /** 服务接口；RPC、其他 */
    private final String interfaceName;
    /** 服务方法；RPC#method */
    private final String methodName;
    /** 网关接口 */
    private final String uri;
    /** 接口类型；GET、POST、PUT、DELETE */
    private final HttpCommandType httpCommandType;

    public HttpStatement(String application, String interfaceName, String methodName, String uri, HttpCommandType httpCommandType) {
        this.application = application;
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.uri = uri;
        this.httpCommandType = httpCommandType;
    }

    public String getApplication() {
        return application;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public String getUri() {
        return uri;
    }

    public HttpCommandType getHttpCommandType() {
        return httpCommandType;
    }

}