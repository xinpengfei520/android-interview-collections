# JVM与Dalvik比较

 - Dalvik 是基于寄存器的，寄存器的编译和运行更快一些，而 JVM 是基于栈的；
 - Dalvik 执行的是 .dex 文件，.dex 文件是多个 .class 文件经过压缩后产生的，而 JVM 执行的是 .class 文件；
 - Dalvik 经过优化，允许内存中存在多个 Dalvik 实例，每一个 APP 的启动都会运行在单独的 Dalvik 实例中；
 - 