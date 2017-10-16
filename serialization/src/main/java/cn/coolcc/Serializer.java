package cn.coolcc;

/**
 * Created by chencheng on 17/9/20.
 */
public interface Serializer {
    byte[] serialize(Object o);

    <T> T deserialize(byte[] bytes, Class<T> t);
}
