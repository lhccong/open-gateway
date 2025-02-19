package com.cong.gateway.bind;

import com.cong.gateway.mapping.HttpCommandType;
import com.cong.gateway.session.Configuration;
import com.cong.gateway.session.GatewaySession;

import java.lang.reflect.Method;

/**
 * Mapper 方法
 *
 * @author cong
 * @date 2025/02/19
 */
public class MapperMethod {
    private String uri;
    private final HttpCommandType command;

    public MapperMethod(String uri, Method method, Configuration configuration) {
        this.uri = uri;
        this.command = configuration.getHttpStatement(uri).getHttpCommandType();
    }

    public Object execute(GatewaySession session, Object args){
        Object result = null;
        switch (command){
            case GET:
                result = session.get(uri, args);
                break;
            case POST:
                break;
            case PUT:
                break;
            case DELETE:
                break;
            default:
                throw new RuntimeException("Unknown execution method for: " + command);
        }
        return result;
    }
}
