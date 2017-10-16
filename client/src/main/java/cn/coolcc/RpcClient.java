package cn.coolcc;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.log4j.Logger;

import java.lang.reflect.Method;
import java.net.InetSocketAddress;
import java.util.concurrent.CountDownLatch;

public class RpcClient {
    private static final Logger LOGGER = Logger.getLogger(RpcClient.class);

    private static RpcClientHandler clientHandler;

    static {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        try {
            bootstrap.group(group).channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel channel) throws Exception {
                            channel.pipeline().addLast(new Encoder());
                            channel.pipeline().addLast(new Decoder<>(Response.class));
                            channel.pipeline().addLast(new RpcClientHandler());
                        }
                    }).option(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture f = bootstrap.connect(new InetSocketAddress("localhost", 10086)).sync();
            f.addListener((ChannelFutureListener) channelFuture -> {
                if(channelFuture.isSuccess()){
                    clientHandler = channelFuture.channel().pipeline().get(RpcClientHandler.class);
                }
            });

            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            LOGGER.error("RpcClient call RPC failure.");
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }


    public static void call(final Class<?> clazz, Method method, Object[] args)  {
        CountDownLatch countDownLatch = new CountDownLatch(1);

        Request request = new Request(clazz.getName(), method.getName(), method.getParameterTypes(), args);
        clientHandler.getChannel().writeAndFlush(request).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture channelFuture) throws Exception {
                countDownLatch.countDown();

            }
        });

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
