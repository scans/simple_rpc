package cn.coolcc;

import org.junit.Test;

/**
 * server测试类
 * Created by chencheng on 17/9/20.
 */
public class Bootstrap {

    @Test
    public void start() {
        new RpcServer().bootstrap();
    }
}
