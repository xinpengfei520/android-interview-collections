# Android的事件机制

onTouch 中super 的两层含义：

return true表示消费事件，且同时让点击事件生效要调用super.onTouchEvent(event);

onInterceptTouchEvent只有ViewGroup中才有的方法，反拦截；