import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

/**
 * Created by mtumilowicz on 2019-02-17.
 */
public class Shop {
    private final Semaphore tills;

    Shop(int tills) {
        this.tills = new Semaphore(tills);
    }

    void getCashTable(int customerID) {
        try {
            System.out.println("Customer " + customerID + " wants to finalize shopping.");
            tills.acquire();
        } catch (InterruptedException e) {
            // not used
        }
    }

    void freeCashTable(int customerID) {
        System.out.println("Customer " + customerID + " payed and the till if free.");
        tills.release();
    }

    public static void main(String[] args) {
        Shop shop = new Shop(2);

        Stream.iterate(0, i -> i < 5, i -> ++i)
                .map(i -> new Customer(shop, i))
                .forEach(Thread::start);

    }
}

class Customer extends Thread {

    private final Shop shop;
    private final int id;

    Customer(Shop shop, int customerId) {
        this.shop = shop;
        this.id = customerId;
    }

    @Override
    public void run() {
        shop.getCashTable(id);
        try {
            System.out.println("GO TO THE TILL: Customer " + id + " approaches the till and starts to finalize shopping");
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50) + 3);
            System.out.println("LEAVE THE TILL: Customer " + id + " is about to free the till");
        } catch (InterruptedException e) {
            // not used
        } finally {
            shop.freeCashTable(id);
        }
    }
}