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
* useful methods:
    * `void acquire() throws InterruptedException`
        * acquires a permit, if one is available and returns immediately, 
        reducing the number of available permits by one
        * 
    * `void acquire(int permits)`
    * `void acquireUninterruptibly()`
    * `void acquireUninterruptibly(int permits)`
    * `boolean tryAcquire()`
    * `boolean tryAcquire(int permits)`
    * `boolean tryAcquire(long timeout, TimeUnit unit)`
    * `boolean tryAcquire(int permits, long timeout, TimeUnit unit)`
    * `void release()`
        * releases a permit, increasing the number of available permits by one
        * calling `release()` more times we call `acquire()` -> more permits that we start with
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