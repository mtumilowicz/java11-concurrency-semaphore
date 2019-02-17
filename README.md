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
