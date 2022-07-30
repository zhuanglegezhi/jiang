document：https://prometheus.fuckcloudnative.io/di-er-zhang-gai-nian/data_model



### 样本

在时间序列中的每一个点称为一个样本（sample），样本由以下三部分组成：

- 指标（metric）：指标名称和描述当前样本特征的 labelsets；

- 时间戳（timestamp）：一个精确到毫秒的时间戳；

- 样本值（value）： 一个 folat64 的浮点型数据表示当前样本的值。





# 指标类型

## 1. Counter（计数器）

Counter 类型代表一种样本数据单调递增的指标，即只增不减，除非监控系统发生了重置。例如，你可以使用 counter 类型的指标来表示服务的请求数、已完成的任务数、错误发生的次数等。



## 2. Gauge（仪表盘）

Gauge 类型代表一种样本数据可以任意变化的指标，即可增可减。Gauge 通常用于像温度或者内存使用率这种指标数据，也可以表示能随时增加或减少的“总数”



## 3. Histogram（直方图）

在大多数情况下人们都倾向于使用某些量化指标的平均值，例如 CPU 的平均使用率、页面的平均响应时间。这种方式的问题很明显，以系统 API 调用的平均响应时间为例：如果大多数 API 请求都维持在 100ms 的响应时间范围内，而个别请求的响应时间需要 5s，那么就会导致某些 WEB 页面的响应时间落到中位数的情况，而这种现象被称为**长尾问题**。

## 4. Summary（摘要）

与 Histogram 类型类似，用于表示一段时间内的数据采样结果（通常是请求持续时间或响应大小等），但它直接存储了分位数（通过客户端计算，然后展示出来），而不是通过区间来计算。



### Histogram VS Summary 

- 它们都包含了 `<basename>_sum` 和 `<basename>_count` 指标
- Histogram 需要通过 `<basename>_bucket` 来计算分位数，而 Summary 则直接存储了分位数的值。
