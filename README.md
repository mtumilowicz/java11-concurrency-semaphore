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
* if any of two cashdesks is free you approach
* if all cashdesks is occupied you wait in a queue

# project description
We will provide simple solution to mentioned earlier problem.