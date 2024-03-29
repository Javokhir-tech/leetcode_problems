### Exchanger

As the name indicates, Exchanger is a construct that can be used to make bidirectional transfers of objects between two threads. Each thread invokes the exchange() method with an item the thread wants to exchange with the other thread. A thread blocks when making the exchange() call until the other thread invokes the exchange() method.

In case of multiple threads, it is not possible for a thread to choose its partner to exchange objects with. Note that the exchange() must be invoked an even number of times during the course of a program to ensure no thread remains blocked when the program exits.

An Exchanger may be viewed as a bidirectional form of a SynchronousQueue. Exchangers may be useful in applications such as genetic algorithms and pipeline designs.