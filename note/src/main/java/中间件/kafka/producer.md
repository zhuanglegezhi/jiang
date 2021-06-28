### acks
- 1: 默认值即为1。生产者发送消息之后，只要分区的leader副本成功写入消息，那么它就会收到来自服务端的成功响应 
- 0：生产者发送消息之后不需要等待任何服务端的响应
- -1/all：生产者在消息发送之后，需要等待`ISR`中的所有副本都成功写入消息之后才能够收到来自服务端的成功响应

### 幂等性

Kafka内部会自动为每个Producer分配一个producer id(PID)，broker端会为producer每个Partition维护一个<PID,Partition> -> sequence number映射。sequence number时从0开始单调递增的。

1. PID（Producer ID），用来标识每个 producer client；
2. sequence numbers，client 发送的每条消息都会带相应的 sequence number，Server 端就是根据这个值来判断数据是否重复。

对于新接受到的消息，broker端会进行如下判断：

- 如果新消息的sequence number正好是broker端维护的<PID,Partition> -> sequence number大1，说broker会接受处理这条消息。
- 如果新消息的sequence number比broker端维护的sequence number要小，说明时重复消息，broker可以将其直接丢弃
- 如果新消息的sequence number比broker端维护的sequence number要大过1，说明中间存在了丢数据的情况，那么会响应该情况，对应的Producer会抛出OutOfOrderSequenceException。

refer：
http://matt33.com/2018/11/04/kafka-transaction/
http://matt33.com/2018/10/24/kafka-idempotent/

### BufferPool

Refer：https://objcoding.com/2020/09/13/kafka-producer-cache-pool/
