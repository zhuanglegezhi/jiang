package objPool;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * Created by zz on 2022/3/1.
 */
public class StringPoolFac extends BasePooledObjectFactory<String> {

    public StringPoolFac() {
        super();
    }

    @Override
    public String create() throws Exception {
        return "str-val-";
    }

    @Override
    public PooledObject<String> wrap(String s) {
        return new DefaultPooledObject<>(s);
    }

    @Override
    public void destroyObject(PooledObject<String> p) throws Exception {
    }

    @Override
    public boolean validateObject(PooledObject<String> p) {
        return super.validateObject(p);
    }

    @Override
    public void activateObject(PooledObject<String> p) throws Exception {
        super.activateObject(p);
    }

    @Override
    public void passivateObject(PooledObject<String> p) throws Exception {
        super.passivateObject(p);
    }
}
