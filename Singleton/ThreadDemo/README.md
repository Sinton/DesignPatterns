#### ***线程示例***

###### **设计问题**
> 在规定好的类中使用懒汉式或者饿汉式实现多线之间的操作，用`Singleton`模式设计解决方案。

###### **设计方案**
> 1. 方案实现的核心主要集中在线程类中的`run()`方法以及`getInstance()`方法
> 2. UML类图如下所示
> ![](http://homework.0x1010.com/screenshot/designpatterns/03-01.png)

###### **运行结果及效果分析**
> - 运行结果
> 
> ![](http://homework.0x1010.com/screenshot/designpatterns/03-02.png)
> 
> - 效果分析
> 由于有`synchronized`关键字的修饰，对线程进行了加锁操作只有当线程执行结束之后别的线程才能进来

###### **主要核心代码**
**`getInstance()` 方法**
 >![](http://homework.0x1010.com/screenshot/designpatterns/03-03.png)

**`run()` 方法**
```java
@Override
	public void run() {
		LimitInstanceClass object = LimitInstanceClass.getInstance();
		while (object == null)
			object = LimitInstanceClass.getInstance();
		object.writeAccessMessage("正在执行" + currentThread().getName());
		try {
			sleep(random.nextInt(5) * 1000);
			object.printAccessMessage();
			object.release();
			System.out.println("该对象已经被" + currentThread().getName() + "释放");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
```

###### **实验总结**
> 单例设计模式的意图是为了确保一个类有且仅有一个实例，并为它提供一个全局访问点，这个实验对单例进行了扩展，以配置文件规定了实例个数，有点像线程池的味道