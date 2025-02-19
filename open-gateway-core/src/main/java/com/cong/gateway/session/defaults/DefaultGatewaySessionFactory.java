package com.cong.gateway.session.defaults;


import com.cong.gateway.session.Configuration;
import com.cong.gateway.session.GatewaySession;
import com.cong.gateway.session.GatewaySessionFactory;

/**
 * 默认网关会话工厂
 * @author cong
 * @date 2025/02/19
 */
public class DefaultGatewaySessionFactory implements GatewaySessionFactory {

    private final Configuration configuration;

    public DefaultGatewaySessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public GatewaySession openSession() {
        return new DefaultGatewaySession(configuration);
    }

}
