import org.junit.Test;
import spring.proxy.IUserDao;
import spring.proxy.ProxyFactory;
import spring.proxy.UserDao;

/**
 * Created by zz on 2021/11/14.
 */
public class TestProxy {
    @Test
    public void testDynamicProxy() {
        IUserDao target = new UserDao();
        System.out.println(target.getClass());  //输出目标对象信息
        IUserDao proxy = (IUserDao) new ProxyFactory(target).getProxyInstance();
        System.out.println(proxy.getClass());  //输出代理对象信息
        proxy.save();  //执行代理方法
    }
}
