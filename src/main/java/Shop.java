import java.util.concurrent.Semaphore;

/**
 * Created by mtumilowicz on 2019-02-17.
 */
class Shop {
    private final Semaphore tills;

    Shop(int tills) {
        this.tills = new Semaphore(tills);
    }

    void takeTillBy(int customerId) {
        try {
            System.out.println("Customer " + customerId + " wants to finalize shopping.");
            tills.acquire();
        } catch (InterruptedException e) {
            // not used
        }
    }

    void releaseTillBy(int customerId) {
        System.out.println("Customer " + customerId + " payed and the till is free.");
        tills.release();
    }
}

