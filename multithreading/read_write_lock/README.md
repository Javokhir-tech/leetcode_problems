### ReadWrite Lock

Imagine you have an application where you have multiple readers and multiple writers. You are asked to design a lock which lets multiple readers read at the same time, but only one writer write at a time.
* acquireReadLock
* releaseReadLock
* acquireWriteLock
* releaseWriteLock