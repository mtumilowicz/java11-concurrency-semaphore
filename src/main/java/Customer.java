import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by mtumilowicz on 2019-02-17.
 */
class Customer extends Thread {

    private final Shop shop;
    private final int id;

    Customer(Shop shop, int customerId) {
        this.shop = shop;
        this.id = customerId;
    }

    @Override
    public void run() {
        shop.takeTillBy(id);
        try {
            System.out.println("Customer " + id + " approaches the till and starts to finalize shopping");
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50) + 3);
            System.out.println("Customer " + id + " is about to free the till");
        } catch (InterruptedException e) {
            // not used
        } finally {
            shop.releaseTillBy(id);
        }
    }
}
