

### ZAB 协议

广播

1）在zookeeper集群中，数据副本的传递策略就是采用消息广播模式。zookeeper中数据副本的同步方式与二段提交相似，但是却又不同。二段提交要求协调者必须等到所有的参与者全部反馈ACK确认消息后，再发送commit消息。要求所有的参与者要么全部成功，要么全部失败。二段提交会产生严重的阻塞问题。

2）Zab协议中 Leader 等待 Follower 的ACK反馈消息是指"只要半数以上的Follower成功反馈即可，不需要收到全部Follower反馈"

refer:
https://www.huaweicloud.com/articles/b696779df9cf3272db9695a83efae29f.html


### 选举
- ZooKeeper 选举会发生在服务器初始状态和运行状态下。
- 初始状态下会根据服务器 `sid` 的编号对比，编号越大权值越大，投票过半数即可选出 Leader。
- Leader 故障会触发新一轮选举，`zxid` 代表数据越新，权值也就越大。
- 在运行期选举还可能会遇到脑裂的情况

zxid: 事务id

- 越大越新
- 前 32 位用来记录 epoch，后 32 位就是用来计数


节点状态

- LOOKING，正在寻找 Leader，处于此阶段的办事处不能对外提供服务
- LEADING，当前办事处就是 Leader，可以对外提供服务
- FOLLOWING，当前办事处正在跟随 Leader，可以对外提供服务
