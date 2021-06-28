### acks
- 1: 默认值即为1。生产者发送消息之后，只要分区的leader副本成功写入消息，那么它就会收到来自服务端的成功响应 
- 0：生产者发送消息之后不需要等待任何服务端的响应
- -1/all：生产者在消息发送之后，需要等待`ISR`中的所有副本都成功写入消息之后才能够收到来自服务端的成功响应



### BufferPool

Refer：https://objcoding.com/2020/09/13/kafka-producer-cache-pool/

