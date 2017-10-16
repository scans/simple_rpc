package cn.coolcc;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by chencheng on 17/9/20.
 */
public class Decoder<T> extends ByteToMessageDecoder {

    private Class<T> clazz;

    public Decoder(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        if (byteBuf.readableBytes() < 4) {
            return;
        }
        int length = byteBuf.readInt();

        if (byteBuf.readableBytes() < length) {
            throw new RuntimeException("字节数不足");
        }

        byte[] bytes = new byte[length];

        byteBuf.readBytes(bytes);

        T t = SerializerFactory.load().deserialize(bytes, clazz);

        list.add(t);
    }
}
