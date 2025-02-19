package com.cong.gateway.session;

import com.cong.gateway.bind.IGenericReference;

/**
 * 网关会话
 *
 * @author cong
 * @date 2025/02/19
 */
public interface GatewaySession {

    Object get(String uri, Object parameter);

    IGenericReference getMapper(String uri);

    Configuration getConfiguration();

}