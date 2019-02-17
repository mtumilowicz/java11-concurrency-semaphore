import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by mtumilowicz on 2019-02-17.
 */
public class ShopTest {

    @Test
    public void shoppingDay() throws InterruptedException {
        Shop shop = new Shop(2);

        Stream.iterate(0, i -> i < 5, i -> ++i)
                .map(i -> new Customer(shop, i))
                .forEach(Thread::start);

        TimeUnit.SECONDS.sleep(2);
    }
}