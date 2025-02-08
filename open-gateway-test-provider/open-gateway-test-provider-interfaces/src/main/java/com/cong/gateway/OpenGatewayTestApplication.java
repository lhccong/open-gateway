package com.cong.gateway;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Configurable
@EnableDubbo
public class OpenGatewayTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(OpenGatewayTestApplication.class, args);
    }

}
