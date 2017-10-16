package cn.coolcc;

import java.util.ServiceLoader;

/**
 * Created by chencheng on 17/9/20.
 */
public class SerializerFactory {

    public static Serializer load() {
        return ServiceLoader.load(Serializer.class).iterator().next();
    }

}
