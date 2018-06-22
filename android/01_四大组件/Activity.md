# 四大组件之Activity

## 1.概念

Activity 是用来能让用户操作并与之交互的界面，一个应用有多个界面，也就是说有多个 Activity,
一般情况下，每一个Activity 都会对应一个 xml 布局文件，所以每一个 Activity都会调用 **setContentView();** 方法来将布局文件和Activity 进行绑定；

Activity 的创建是系统通过反射的方式创建，因为在AndroidMenifest.xml文件中注册了一个Activity的全类名；

### 启动方式

一般启动：

带回调启动：

### 生命周期

### 四种启动模式

## Intent 

Intent 是 Actvity、Service、Broadcast receiver 三个应用组件之间通信的信使，注意Intent不是四大组件之一；

①Intent可以知道去哪；
②可以携带数据；

分类：显示意图和隐式意图（跨应用）； 
显示意图：只能访问本应用、本进程中的指定的组件；
隐式意图：可以跨应用访问，没有明确指定目标组件的意图；


在Android 中，用户界面控件被封装成各种View ，每一个View 是一个可以显示的控件对象，如TextView控件，而每一个对象都有各自的属性，通过使用不同的对象我们可以来实现各种各样的显示效果，

## Context

Context翻译过来就是上下文的意思，Context是Activity Service的父类，所以Activty、 Service本身就是一个 Context



