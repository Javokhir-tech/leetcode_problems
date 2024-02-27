### Asynchronous to Synchronous Problem

A real-life interview question asking to convert asynchronous execution to synchronous execution.

Imagine we have an Executor class that performs some useful task asynchronously via the method asynchronousExecution(). In addition the method accepts a callback object which implements the Callback interface. the object’s done() gets invoked when the asynchronous execution is done.
Note how the main thread exits before the asynchronous execution is completed.

Your task is to make the execution synchronous without changing the original classes (imagine, you are given the binaries and not the source code) so that main thread waits till asynchronous execution is complete. In other words, the highlighted line#8 only executes once the asynchronous task is complete.