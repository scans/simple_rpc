package cn.coolcc;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class RpcClientHandler extends SimpleChannelInboundHandler<Response> {

    private volatile Channel channel;

    @Override
    protected void channelRead0(ChannelHandlerContext context, Response resp)
            throws Exception {
        System.out.println("RpcClientHandler - response: " + resp);

        System.out.println(resp.getRequestId());
        System.out.println(resp.getResult());
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        channel = ctx.channel();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }

    public Channel getChannel() {
        return channel;
    }
}
