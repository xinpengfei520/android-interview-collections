# Service

Service是一个服务，他不关联任何的用户界面，它在后台运行，应用退出它不会被销毁，再次进入应用，它还是之前创建的Service对象；

## Service的生命周期

### 一般启动

### 绑定启动

bind方式可以实现Activity 向Service传递数据，反之，Service可以通过onBind();将数据返回给Activity.

