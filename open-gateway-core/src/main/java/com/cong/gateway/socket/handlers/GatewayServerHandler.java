package com.cong.gateway.socket.handlers;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.cong.gateway.bind.IGenericReference;
import com.cong.gateway.socket.BaseHandler;
import com.cong.gateway.session.GatewaySession;
import com.cong.gateway.session.defaults.DefaultGatewaySessionFactory;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 会话服务器处理程序
 *
 * @author cong
 * @date 2025/02/07
 */
public class GatewayServerHandler extends BaseHandler<FullHttpRequest> {

    private final Logger logger = LoggerFactory.getLogger(GatewayServerHandler.class);

    private final DefaultGatewaySessionFactory gatewaySessionFactory;

    public GatewayServerHandler(DefaultGatewaySessionFactory gatewaySessionFactory) {
        this.gatewaySessionFactory = gatewaySessionFactory;
    }


    @Override
    protected void session(ChannelHandlerContext ctx, Channel channel, FullHttpRequest request) {
        logger.info("网关接收请求 uri：{} method：{}", request.uri(), request.method());
        // 返回信息控制：简单处理
        String uri = request.uri();
        String methodName = request.uri().substring(1);
        if ("favicon.ico".equals(methodName)) {
            return;
        }

        // 服务泛化调用
        GatewaySession gatewaySession = gatewaySessionFactory.openSession();
        IGenericReference reference = gatewaySession.getMapper(uri);
        String result = reference.$invoke("cong") + " " + System.currentTimeMillis();

        // 返回信息处理
        DefaultFullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK);
        // 设置回写数据
        response.content().writeBytes(JSON.toJSONBytes(result, SerializerFeature.PrettyFormat));
        // 头部信息设置
        HttpHeaders heads = response.headers();
        // 返回内容类型
        heads.add(HttpHeaderNames.CONTENT_TYPE, HttpHeaderValues.APPLICATION_JSON + "; charset=UTF-8");
        // 响应体的长度
        heads.add(HttpHeaderNames.CONTENT_LENGTH, response.content().readableBytes());
        // 配置持久连接
        heads.add(HttpHeaderNames.CONNECTION, HttpHeaderValues.KEEP_ALIVE);
        // 配置跨域访问
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_ORIGIN, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_HEADERS, "*");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, DELETE");
        heads.add(HttpHeaderNames.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
        channel.writeAndFlush(response);
    }
}
