[![Build Status](https://travis-ci.com/mtumilowicz/java11-concurrency-semaphore.svg?branch=master)](https://travis-ci.com/mtumilowicz/java11-concurrency-semaphore)

# java11-concurrency-semaphore

_Reference_: https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/util/concurrent/Semaphore.html

# preface
* semaphore controls the number of threads that can access 
a resource (critical section)
* difference to synchronized block - semaphore allows access 
to `N` threads
* if `N == 1` -> mutually exclusive access
* semaphore internally has a number of permits
    * thread acquires and releases a permit
    * if there is no free permit to take - thread is forced to wait

# java
* semaphores in Java are represented by `Semaphores` class
* constructors
    * `Semaphore(int permits)`
    * `Semaphore(int permits, boolean fair)`
        * `fair` denotes if this semaphore will guarantee
         FIFO granting of permits under contention
* useful methods:
    * `void acquire() throws InterruptedException`
        * acquires a permit, if one is available and returns immediately, 
        reducing the number of available permits by one
        * blocks if a permit is not available
        * thread could be interrupted while waiting for permit
    * `void acquire(int permits)`
    * `void acquireUninterruptibly()`
        * can't be interrupted while waiting for permit
    * `void acquireUninterruptibly(int permits)`
    * `boolean tryAcquire()` - acquires a permit from this semaphore, only if one is available at the
        time of invocation
    * `boolean tryAcquire(int permits)`
    * `boolean tryAcquire(long timeout, TimeUnit unit)`
    * `boolean tryAcquire(int permits, long timeout, TimeUnit unit)`
    * `void release()`
        * releases a permit, increasing the number of available permits by one
        * calling `release()` more times we call `acquire()` -> more permits that we start with
    * `void release(int permits)`
    * `int availablePermits()`
    * `int drainPermits()`
        * acquires and returns all permits that are immediately available
    * `void reducePermits(int reduction)`
* permit is acquired on a semaphore basis: one thread can acquire a permit 
and another can return it
# real-life example
* one queue (FIFO) to two cashTable
* if any of two tills is free you may approach
* if all tills are occupied you wait in a queue

# project description
We will provide simple solution to mentioned earlier problem.
1. shop
    ```
    class Shop {
        private final Semaphore tills;
    
        Shop(int tills) {
            this.tills = new Semaphore(tills);
        }
    
        void getCashTable(int customerId) {
            try {
                System.out.println("Customer " + customerId + " wants to finalize shopping.");
                tills.acquire();
            } catch (InterruptedException e) {
                // not used
            }
        }
    
        void freeCashTable(int customerId) {
            System.out.println("Customer " + customerId + " payed and the till is free.");
            tills.release();
        }
    }
    ```
1. customer
    ```
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
                System.out.println("Customer " + id + " approaches the till and starts to finalize shopping");
                TimeUnit.MILLISECONDS.sleep(new Random().nextInt(50) + 3);
                System.out.println("Customer " + id + " is about to free the till");
            } catch (InterruptedException e) {
                // not used
            } finally {
                shop.freeCashTable(id);
            }
        }
    }
    ```
1. simulation
    ```
    Shop shop = new Shop(2);
    
    for (int i = 0; i < 5; i++) {
        var customer = new Customer(shop, i);
        customer.start();
        customer.join();
    }
    ```
    could produce output:
    ```
    Customer 4 wants to finalize shopping.
    Customer 0 wants to finalize shopping.
    Customer 1 wants to finalize shopping.
    
    Customer 1 approaches the till and starts to finalize shopping
    
    Customer 2 wants to finalize shopping.
    Customer 3 wants to finalize shopping.
    
    Customer 0 approaches the till and starts to finalize shopping
    Customer 0 is about to free the till
    Customer 0 payed and the till is free.
    
    Customer 4 approaches the till and starts to finalize shopping
    
    Customer 1 is about to free the till
    Customer 1 payed and the till is free.
    
    Customer 2 approaches the till and starts to finalize shopping
    
    Customer 4 is about to free the till
    Customer 4 payed and the till is free.
    
    Customer 3 approaches the till and starts to finalize shopping
    
    Customer 2 is about to free the till
    Customer 2 payed and the till is free.
    
    Customer 3 is about to free the till
    Customer 3 payed and the till is free.
    ```
    **as you may notice - at every point in time no more than 
    two tills are occupied**