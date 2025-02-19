package com.cong.gateway.socket;

import com.cong.gateway.session.defaults.DefaultGatewaySessionFactory;
import com.cong.gateway.socket.handlers.GatewayServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

/**
 * 网关通道初始化器
 *
 * @author cong
 * @date 2025/02/19
 */
public class GatewayChannelInitializer extends ChannelInitializer<SocketChannel> {

    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewayChannelInitializer(DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }


    @Override
    protected void initChannel(SocketChannel channel) {
        ChannelPipeline line = channel.pipeline();
        //解码器
        line.addLast(new HttpRequestDecoder());
        //编码器
        line.addLast(new HttpResponseEncoder());
        //聚合器 用于处理除了 GET 请求外的 POST 请求时候的对象信息
        line.addLast(new HttpObjectAggregator(1024 * 1024));
        //自定义处理器
        line.addLast(new GatewayServerHandler(gatewaySessionFactory));
    }
}
