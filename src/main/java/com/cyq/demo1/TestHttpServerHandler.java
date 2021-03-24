package com.cyq.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

/**
 * @author chenyiqing
 * @date 2021/03/24 23:19
 **/
public class TestHttpServerHandler extends SimpleChannelInboundHandler<HttpObject> {

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        if (msg instanceof HttpRequest) {
            ByteBuf content = Unpooled.copiedBuffer("hello world", CharsetUtil.UTF_8);
            FullHttpResponse response =
                new DefaultFullHttpResponse(HttpVersion.HTTP_1_0, HttpResponseStatus.OK, content);
            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(response);
        }

    }
}
