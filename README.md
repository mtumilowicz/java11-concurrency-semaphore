# java11-concurrency-semaphore

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
* useful methods:
    * `void acquire() throws InterruptedException`
        * acquires a permit, if one is available and returns immediately, 
        reducing the number of available permits by one
        * 
    * `void acquireUninterruptibly()`
    * `void acquire(int permits)`
    * `void acquireUninterruptibly(int permits)`
    * `boolean tryAcquire()`
    * `boolean tryAcquire(long timeout, TimeUnit unit)`
    * `boolean tryAcquire(int permits)`
    * `boolean tryAcquire(int permits, long timeout, TimeUnit unit)`
    * `void release()`
        * releases a permit, increasing the number of available permits by one
        * 
    * `void release(int permits)`
    * `int availablePermits()`
    * `int drainPermits()`
    * `void reducePermits(int reduction)`
# real-life example
* one queue (FIFO) to two cashTable
* if any of two cashdesks is free you approach
* if all cashdesks is occupied you wait in a queue

# project description
We will provide simple solution to mentioned earlier problem.