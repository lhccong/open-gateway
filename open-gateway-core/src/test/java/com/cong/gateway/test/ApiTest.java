package com.cong.gateway.test;

import com.cong.gateway.session.Configuration;
import com.cong.gateway.session.GenericReferenceSessionFactoryBuilder;
import io.netty.channel.Channel;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class ApiTest {

    private final Logger logger = LoggerFactory.getLogger(ApiTest.class);

    @Test
    public void test_GenericReference() throws InterruptedException, ExecutionException {
        Configuration configuration = new Configuration();
        configuration.addGenericReference("open-gateway-test", "com.cong.gateway.rpc.IActivityBooth", "hello");

        GenericReferenceSessionFactoryBuilder builder = new GenericReferenceSessionFactoryBuilder();
        Future<Channel> future = builder.build(configuration);

        logger.info("服务启动完成 {}", future.get().id());

        Thread.sleep(Long.MAX_VALUE);
    }

}