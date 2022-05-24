

题目描述

实现一个熔断器CircuitBreaker

发给候选人版本





详细版本

- 提供用户友好的使用接口

- 当异常达到阈值（失败率阈值，最小数量）可以进行熔断

- 如果在熔断状态下可以自恢复（某个时间后，放部分流量进来尝试恢复，都成功则->closed，否则opened）

  

附加项

- 线程池隔离，讨论即可



核心点

- 调用数据度量统计。
- 维护断路器自身的状态。
- 基于前两点保护包裹在断路器中执行的调用。



参考：

https://github.com/lmhmhl/Resilience4j-Guides-Chinese/blob/main/core-modules/CircuitBreaker.md

https://www.cnblogs.com/throwable/p/13906733.html

