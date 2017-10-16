package cn.coolcc;

import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Created by chencheng on 17/9/20.
 */
public class RpcServerHandler extends SimpleChannelInboundHandler<Request> {
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Request request) throws Exception {
        //todo 处理对应的请求，处理完成通过channelHandlerContext写回给客户端
        System.out.println(request);

        channelHandlerContext.writeAndFlush(new Response()).addListener((ChannelFutureListener) channelFuture -> System.out.println("success"));
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
