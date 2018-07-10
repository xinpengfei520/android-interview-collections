
## 1.Activity

Activity 是用来能让用户操作并与之交互的界面，一个应用有多个界面，也就是说有多个 Activity,
一般情况下，每一个Activity 都会对应一个 xml 布局文件，所以每一个 Activity都会调用 **setContentView();** 方法来将布局文件和Activity 进行绑定；

Activity 的创建是系统通过反射的方式创建，因为在AndroidMenifest.xml文件中注册了一个Activity的全类名；另外它还是Android中的四大组件之一，Context的子类。

### 1.1启动方式

一般启动：startActivity();

带回调启动：startActivityForResult();

### 1.2生命周期

onCreate() --> onStart() --> onResume() --> onPause() --> onStop() --> onRestart() --> onDestory();

A Activity 跳转到 B Activity 声明周期变化：

A Activity 从 onResume() --> onPause() -->  B Activity的 onCreate() --> onStart() --> onResume() --> A Activity onStop();

如果 B Activity 是透明的，那么是不会执行 A Activity onStop();

我们可以这么理解：

 - onPause():可见不可操作；
 - onStop():不可见不可操作;

### 1.3四种启动模式

1. Standard:默认标准的启动模式
2. SingleTop:单一顶部，如果要启动的Activity在Activity任务栈的顶部，则不启动
3. SingleTask:单一任务栈，如果要启动的Activity在Activity任务栈中存在那么就将其上面的其他的Activity弹出栈。
4. SingleInstance:单例模式，整个手机操作系统中，只有一个实例存在，并且它运行在独立的任务栈中，且只有它一个实例，不同的应用打开这个Activity，共享共用的是这同一个Activity.

### 1.4 设置Activity的转场动画

- 通过自定义 Activity 的主题；
- 通过覆写Activity 的 overridePendingTransition 方法；

### 1.5 Activity 横竖屏切换

如果不在 AndroidMenifest.xml 文件中设置 android:configChanges="oritation|keyboardHidden|ScreenSize" 时，横竖屏切换回导致重新调用Activity的各个生命周期方法，默认是先销毁然后重新创建。

### 1.6 将 Activity 设置成窗口样式

 - 可以自定义一个Activity Theme;
 - android:theme="@android:style/Theme.Dialog"

### 1.7 如果你后台的 Activity 被系统回收怎么办？如何在被回收前保存状态？

被回收属于异常终止的情况，可以在onSaveInstanceState这个方法中保存一些状态，然后在onRestoreInstanceState 这个方法的中恢复保存的状态，即再次被创建的时候会自动调用。

正常情况下我们在onStop()方法中保存一个状态。

### 1.8 如何退出Activity

- 直接调用finish();
- 在BaseActivity中保存记录一个Activity 栈，需要退出时，进行出栈操作即可；
- 发送特定的广播；

### 1.9 Activity之间如何传递数据

- Intent;
- SharedPreference;
- 数据库存储；
- bundle;
- 发送广播等；
- 采用第三发如 EventBus等。

### 1.10 同一个app的不同Activity可以放在不同的任务栈中吗？

可以，我们可以在启动一个Activity 的时候设置 Intent的Flag 标志为 new task，启动后它会放到不同的任务栈中。

## 2.Intent

Intent 是 Actvity、Service、Broadcast receiver 三个应用组件之间通信的信使，注意Intent不是四大组件之一；

①Intent可以知道去哪；
②可以携带数据；

分类：显示意图和隐式意图（跨应用）； 
显示意图：只能访问本应用、本进程中的指定的组件；
隐式意图：可以跨应用访问，没有明确指定目标组件的意图；


在Android 中，用户界面控件被封装成各种View ，每一个View 是一个可以显示的控件对象，如TextView控件，而每一个对象都有各自的属性，通过使用不同的对象我们可以来实现各种各样的显示效果

## 3.Context

Context翻译过来就是上下文的意思，它描述了一个应用程序的环境信息，方便我们可以简单的访问到各种资源，Context是一个抽象类，Context 是 Activity Service 及 Application 的父类，所以Activty、 Service本身就是一个 Context，Android中提供了改类的具体实现类 ContextImpl,我们可以使用Context启动一个Activity，发送广播，接收Intent信息等。在使用Context的时候我们需要注意内存泄漏，尽量使用Application的Context来代替Activity的Context,因为Application的Context的声明周期是伴随整个应用的声明周期。

## 4.Service

Service是一个服务，他不关联任何的用户界面，它在后台运行，应用退出它不会被销毁，再次进入应用，它还是之前创建的Service对象；默认情况下，Service是和Activity 一样运行在当前 app 所在进程的 main Thread 里面，也就是说不能再 Service 中执行耗时的操作，网络请求和大文件的读写等，特殊情况下，我们可以配置 Service 执行所在的进程，让Service在另外的进程中执行任务，ActivityManagerService（以后简称AMS）Android中最核心的服务 ， 主要负责系统中四大组件的启动、切换、调度及应用进程的管理和调度等工作，其职责与操作系统中的进程管理和调度模块类似，因此它在Android中非常重要。

### 4.1 什么时候用 Service?

1. 让APP 在后台运行，提高进程的优先级；
2. 在后台执行时间跨度比较长的任务的时候；
3. 在后台下载数据或者播放音乐的时候；

### 4.2 Service的生命周期

一般启动：onCreate() --> onStartCommand() --> onDestory();

绑定启动：onCreate() --> onBind() --> onUnbind() --> onDestory();

### 4.3 启动方式

 - 一般启动
 - 绑定启动

bind方式可以实现Activity 向Service传递数据，反之，Service可以通过onBind();将数据返回给Activity.

### 4.4 Service中可以谈对话框和吐司吗？

可以的，需要加权限：

```
<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW"/>
```

弹吐司有个条件就是需要一个Context，而Service本身就是一个Context，所以谈吐司是完全可以的。

### 4.5 IntentService 及其优点

IntentService 是 Service 的子类，它比普通的Service增加了额外的功能；Service默认情况下，它不会专门启动一个单独的进程，默认和它所在的应用处于同一个进程中，它也不是单独的一个线程，所以它也不可以处理一些耗时的任务；而IntentService是一个抽象类，必须要创建它的子类才可以使用，它可以用于处理后台比较耗时的任务，当任务执行完之后会自动停止运行，另外一方面因为它是服务，所有优先级比较高。

### 4.6 Activity、Intent、Service

1. 他们都是 Android 开发中使用频率比较高的类，Activity/Service 是四大组件之一；
2. 都是Context 的子类 ContextWrapper的子类，Activity负责用户界面的显示和交互，Service负责后台任务的处理；
3. Activity 和 Service通过 Intent来传递数据，因此Intent可以说是他们之间通信的使者；




