package objPool;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

/**
 * Created by zz on 2022/3/1.
 */
@Slf4j
public class StringPoolTest {

    public static void main(String[] args) {
        GenericObjectPoolConfig<String> config = new GenericObjectPoolConfig<>();
        config.setMaxTotal(2);
        config.setMinIdle(1);
        config.setMaxWaitMillis(3000);

        StringPool pool = new StringPool(new StringPoolFac(), config);

        for (int i = 0; i < 3; i++) {
            String s = "";
            try {
                s = pool.borrowObject();
                log.info("str: {}", s);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (!s.equals("")) {
                    pool.returnObject(s);
                }
            }
        }
    }
}
