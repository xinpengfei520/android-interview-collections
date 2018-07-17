
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

 - 一般启动：通过 startService 会首先调用 onCreate() -> onStartCommand() 然后就会处于运行状态，stopService的时候就会调用 onDestroy()，如果是调用者自己退出而没有调用stopService的话，那么Service会一直在后台运行；
 - 绑定启动：首先会调用onCreate() -> onBind()，这个时候调用者和Service绑定在了一起，调用者退出了，Service 就会调用 onUnbind -> onDestroy() 方法，所谓绑定就是一起共存亡了，调用者也可以调用 unbindService来停止服务，这时候Service就会调用 onUnbind() -> onDestroy()方法；

Activity 通过 bindService(Intent service,ServiceConnection conn,int flags) 跟 Service 进行绑定，当绑定成功的时候 Service 会将代理对象通过回调的形式传给 conn，这样我们就拿到了 Service 提供的服务代理对象，bind方式可以实现Activity 向Service传递数据，反之，Service可以通过onBind();将数据返回给Activity。

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

### 4.7 在Service 中新开线程和 Activity 直接新开线程的区别？

1. 直接在Activity 中新开一条线程来做耗时操作，当该Activity 退出到桌面或其他情况时将成为一个背景进程；
2. 在Service中新启动线程，则此时Android会依据进程中当前活跃组件的重要程度，将其判断为服务进程，优先级比 1 高；

## 5.BroadcastReceiver

广播接收器适用于接收系统广播通知用的，例如 SD 卡的挂载，手机重启，电量变化，来电，短信等，这些都是通过广播来启动后台服务，避免被杀死，电量变化的广播等必须动态注册。

### 5.1 分类

- 有序广播：广播可以被中断，数据可以被修改，指定接收者的广播是不可以被拦截掉的，一般调用abortBroadcast();
- 无序广播：不可以被终止，数据不可以被修改；

### 5.2 注册方式

- 静态注册：在 AndroidManifest.xml 文件中进行注册，当 APP 退出后，Receiver 仍然可以接收到广播并且进行相应的处理；
- 动态注册：在代码中注册，当app 退出后，也就没有办法收到广播了，动态注册时要注意避免内存泄漏，即在onDestroy中调用解注册广播的方法；

### 5.3 生命周期

1. 广播接受者的生命周期是非常短暂的，在接收广播的时候创建，onReceive()方法结束之后销毁；
2. 广播接收者中不可以做一些耗时的工作，否则系统会弹出 ANR 对话框；
3. 最好也不要在广播接受者中创建子线程做耗时的工作，因为广播接受者被销毁后进程就成了空进程，很容易被系统杀掉；
4. 耗时较长的工作最好放在服务中完成；

## 6.ContentProvider

ContentProvider 翻译过来就是内容提供者的意思，默认情况下，一个应用的数据库是私有的，也就说其他应用不可以访问，我们要想让其他应用访问我们数据库的表，我们可以通过ContentProvider来将自己应用的数据库表暴露给外面调用，也就说ContentProvider是应用程序间共享数据的接口，ContentProvider是一个抽象类，必须要创建一个自定义类继承于它，然后重写query、insert、update、delete等方法。

### 6.1 为什么要用它？和sql的实现上有什么差别？

 - ContentProvider 屏蔽了数据存储的细节，内部实现对用户完全透明，用户只需要关心操作数据的uri就可以了，ContentProvider 可以实现不同app 之间共享；
 - Sql 也有增删改查的方法，但是 sql 只能查询本应用下的数据库，而 ContentProvider 还可以去增删改查本地文件.xml 文件的读取等；

### 6.2 ContentProvider、ContentResolver、ContentObserver

- ContentProvider：内容提供者，对外提供数据；
- ContentResolver：内容解析者，用于获取提供者提供的数据；
- ContentObserver：内容监听器，可以监听数据的改变状态；

## 7.AIDL 

AIDL 是Android interface definition language 的简称，它是一种接口定义语言，用来解决进程间通信的，它可以生成在 Android 设备上两个进程之间进行通信(IPC)的代码，一般用于在一个进程中调用另一个进程中Service对象的操作。

## 8.组件间通信

1. 通过bundle 设置参数；
2. 通过调用接口；
3. 将数据持久化(SP/sql/文件等)保存到本地，然后再读取；
4. EventBus 通信；
5. ...

## 9.Fragment

Fragment 翻译过来就是碎片的意思，我们可以灵活的使用它来实现各种页面的切换等操作，Fragment 一般都是需要一个容器的，一般是FrameLayout，它是依赖于 Activity的；

### 9.1 Fragment的生命周期

onAttach() -> onCreate() -> onCreateView() -> onActivityCreated() -> onStart() -> onResume() -> onPause() -> onStop() -> onDestroyView() -> onDestroy() -> onDetach();

### 9.2 replace & add 区别

add 的时候是把所有的 Framgent 一层一层的叠加到了 FrameLayout 上了，而 replace 的话首先将该容器中的其他 Fragment 去除掉，然后将当前的 Fragment 添加到容器中。

### 9.3 Fragment 如何实现类似 Activity 栈的压栈和出栈效果？

Fragment 的事务管理器内部维持了一个双向链表结构，该结构可以记录我们每次 add 的 Fragment 和 replace 的 Fragment,然后当我们点击 back 的时候会自动帮我们实现退栈的操作。

入栈：transaction.addToBackStack("name");

### 9.4 如何切换 Fragment 不重新实例化

replace 这个方法只是在上一个 Fragment 不再需要时才用的简便方法，正确的切换方式是 add,切换时 hide,add 另一个Fragment,再次切换时，只需hide 当前，show 另一个即可，这样就能做到 Fragment 在切换时不重新实例化。

### 9.5 FragmentPagerAdapter 与 FragmentStatePagerAdapter 的区别？

- FragmentPagerAdapter:该类中的每一个生成的 Fragment 都将保存在内存中，因此适用于那些相对静态的页面，数量比较少的那种；
- FragmentStatePagerAdapter：该 PagerAdapter 的实现将只保留当前页面，当页面离开视线后，就会被消除，释放其资源；这么实现的好处就是当拥有大量的页面时，不必在内存中占用大量的内存；

## 10.三级缓存的实现


