- bin log
  - `binlog `用于记录数据库执行的写入性操作(不包括查询)信息，以二进制的形式保存在磁盘中
  - 使用场景
    - 主从复制
    - 数据恢复
- redo log
- undo log
  - 实现`MVVC`

redolog时机.png![redolog时机](https://user-images.githubusercontent.com/17567449/123726829-38e9c980-d8c3-11eb-9717-5452247e4d55.png)



