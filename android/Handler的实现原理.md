
# Handler的实现原理

描述：

创建一个Handler对象和Message对象，然后将消息封装后进行发送，放到MessageQueue中，然后由Looper轮训器进行取出Message，然后将Message交给Handler处理完毕之后，最后清理掉Message，如此往复循环下去，完成消息的发送及处理过程；

### Handler实现发送的 4 个方法

SendMessage SendMessageDelayed
SendEmptyMessage SendEmptyMessageDelayed

