## Rate Limiting Using Token Bucket Filter

<p>
This is an actual interview question asked at Uber and Oracle.

Imagine you have a bucket that gets filled with tokens at the rate of 1 token per second. The bucket can hold a maximum of N tokens. Implement a thread-safe class that lets threads get a token when one is available. If no token is available, then the token-requesting threads should block.

The class should expose an API called getToken that various threads can call to get a token
</p>

![img.png](rate_limiting.png)