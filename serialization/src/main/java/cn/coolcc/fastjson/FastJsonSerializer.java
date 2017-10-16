package cn.coolcc.fastjson;

import cn.coolcc.Serializer;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * Created by chencheng on 17/9/20.
 */
public class FastJsonSerializer implements Serializer {
    @Override
    public byte[] serialize(Object o) {
        return JSON.toJSONBytes(o, SerializerFeature.SortField);
    }

    @Override
    public <T> T deserialize(byte[] bytes, Class<T> t) {
        return JSON.parseObject(bytes, t, Feature.SortFeidFastMatch);
    }
}
