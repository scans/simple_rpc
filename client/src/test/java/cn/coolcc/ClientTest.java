package cn.coolcc;

import cn.coolcc.proxy.Proxy;
import org.junit.Test;

/**
 * Created by chencheng on 17/9/20.
 */
public class ClientTest {

    @Test
    public void test() {
        HelloService service = new Proxy<>(HelloService.class).getInstance();
        for (int i = 0; i < 100; i++) {
            service.sayHello("chen");
        }
    }

}
