package cn.coolcc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * Created by chencheng on 17/9/20.
 */
public class Encoder extends MessageToByteEncoder<Request> {
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Request request, ByteBuf byteBuf) throws Exception {
        byte[] bytes = SerializerFactory.load().serialize(request);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
