package com.cong.gateway.session;

import com.cong.gateway.session.handlers.SessionServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class SessionChannelInitializer extends ChannelInitializer<SocketChannel> {
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
        line.addLast(new SessionServerHandler());
    }
}
