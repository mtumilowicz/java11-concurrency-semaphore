import org.junit.Test;

/**
 * Created by mtumilowicz on 2019-02-17.
 */
public class ShopTest {

    @Test
    public void shoppingDay() throws InterruptedException {
        Shop shop = new Shop(2);
        
        for (int i = 0; i < 5; i++) {
            var customer = new Customer(shop, i);
            customer.start();
            customer.join();
        }
    }
}