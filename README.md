## Java设计模式学习笔记 [English](https://github.com/Sinton/DesignPatterns/blob/master/README_EN.md)
### 文档目录结构
> ##### Demo
> - **Strategy(策略模式)/**
>   - *PrinterSalesSystem/*
>     - src/
>       - zjut.model/bean/
>         - Discount.java
>         - Printer.java
>       - zjut.model/discount/
>         - FixedDiscount.java
>         - NewDiscount.java
>         - RateDiscount.java
>         - Undiscounted.java
>       - zjut.model/printer/
>         - CanonPrinter.java
>         - HpPrinter.java
>         - LenovoPrinter.java
>       - zjut.model/utils/
>         - Repository.java
>         - Tools.java
>       - zjut.view/
>         - MainApplication.java

#### ***打印机销售系统设计***

###### **设计问题**
> 实现打印机销售时的折扣计算，你的方案应该能够使得在销售打印机（即使是同一种打印机）时可以灵活的选用折扣计算方法来给出打印机的实际售价，并且可以很容易地增加或修改折扣计算方法而不至于对整个系统的维护造成困难。请用strategy模式设计解决方案。

###### **问题分析与模式选用**
> 1. 由于打印机折扣方式是不固定的，而且折扣方式可能随时都会发生变动，所以就需要动态的改变对象的行为。
> 2. 每一种的折扣方式相对与其他的折扣方式都是互斥且独立的，所以需要将折扣类中经常改变或者将来可能改变的部分提取出来并进行封装，作为一个接口。

###### **设计方案**
> 1. 首先将折扣类`Discount`作为一个`interface`，让不同折扣方式的类分别去实现其不同的折扣方法
> 2. 打印机的种类繁多，可以对打印机类`Printer`进行抽象，提取打印机共性方法和属性字段
> 3. 为了要提高软件的维护性，能动态添加或者减少不同的折扣方式和打印机种类，需要用到`Java`的反射机制，以动态加载需要用到的类对象
> 4. 使用反射机制就需要一个配置文件来定义动态加载的`打印机类`和`折扣类`索对应的`ClassName`
> 5. 增加一个辅助类以及一个工具类，分别用来存储类名称与类文件命之间Key-Value关系图和解析配置文件，读取配置文件内容
> 6. UML类图如下所示
> ![](http://homework.0x1010.com/screenshot/designpatterns/01-01.png)

###### **运行结果及效果分析**
> - 运行结果
> 1. ![](http://homework.0x1010.com/screenshot/designpatterns/01-02.png)
> 2. ![](http://homework.0x1010.com/screenshot/designpatterns/01-03.png)
> - 效果分析
> 1. `Tools`类对配置文件进行解析，将获取到的类名和值以Key-Value的方式存储在`Repository`类中的某个字段
> 2. 在初始化GUI界面时，将`Repository`类中提供的字段数据设置在下拉菜单中
> 3. 在点击“添加到物品清单”按钮时，根据下拉菜单中的选项利用反射机制动态加载该选项所对应的类名，然后调用该类中的方法和字段。然后将数据集添加到列表当中
> 4. 每次在列表中动态添加一个数据集时，动态更新列表中所有的总价合计
> - 扩展效果
> 1. 假设现在又多了一种新的折扣方式——打印机单价满400就打85折，否则按原价出售
> 2. 新建一个折扣类实现新的折扣方法
> 3. 在配置文件`discounts.properties`中添加一个与该折扣类对应的类名（具体如图所示）
> 4. 运行效果
> ![](http://homework.0x1010.com/screenshot/designpatterns/01-04.png)
> ![](http://homework.0x1010.com/screenshot/designpatterns/01-05.png)