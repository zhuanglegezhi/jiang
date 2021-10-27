# 断路器

![avatar](https://github.com/lmhmhl/Resilience4j-Guides-Chinese/raw/main/images/39cdd54-state_machine.jpg)

断路器通过有限状态机实现，有三个普通状态：关闭、开启、半开，还有两个特殊状态：禁用、强制开启。

断路器使用滑动窗口来存储和统计调用的结果。可以选择基于调用数量的滑动窗口或者基于时间的滑动窗口。基于访问数量的滑动窗口统计了最近N次调用的返回结果。居于时间的滑动窗口统计了最近N秒的调用返回结果。






>doc：https://github.com/lmhmhl/Resilience4j-Guides-Chinese/blob/main/index.md