package spring.proxy;

/**
 * Created by zz on 2021/11/14.
 */
public class UserDao implements IUserDao{
    @Override
    public void save() {
        System.out.println("保存数据");
    }
}
