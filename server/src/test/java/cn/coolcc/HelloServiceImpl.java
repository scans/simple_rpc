package cn.coolcc;

/**
 * Created by chencheng on 17/9/20.
 */
public class HelloServiceImpl implements HelloService {
    public void sayHello(String name) {
        System.out.println("Hello " + name);
    }
}
