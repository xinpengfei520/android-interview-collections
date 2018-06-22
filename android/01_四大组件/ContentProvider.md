# ContentProvider

ContentProvider 直译为内容提供者，如果一个应用程序要想把他的的私有数据库表暴露给外面，供其他应用来使用，那么就要使用ContentProvider

context.getContentResolver();

注册Uri的监听
register.ContentObserver

在功能清单中注册相应的name、authority和exported

content://com.xpf.provider.personprovider/1

前缀 + provider name   + 表名 + id