import java.util.concurrent.Semaphore;

/**
 * Created by mtumilowicz on 2019-02-17.
 */
class Shop {
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
}

