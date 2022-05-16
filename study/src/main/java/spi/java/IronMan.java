package spi.java;

/**
 * Created by zz on 2022/5/16.
 */
public class IronMan implements Superman {
    @Override
    public void introduce() {
        System.out.println("我是钢铁侠！");
    }
}
