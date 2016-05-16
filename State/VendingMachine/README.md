#### ***自动售货机***

###### **百度百科官方的概念**
> 一个对象的状态依赖于它的变量的取值情况，对象在不同的运行环境中，可能具有不同的状态。对象调用方法所产生的行为效果依赖于它当时的状态。

###### **核心思想**
> 当对象的状态改变时，同时改变其行为！

###### **意图**
> 主要的意图是将表示对象状态的逻辑分散到代表的不同类中，通俗地讲就是将具体的逻辑业务进行拆分，分解到能代表具体状态的类中

###### **角色组成**
> - 使用环境
> 客户程序是通过它来满足自己的需求。它定义了客户程序需要的接口；并且维护一个	具体状态角色的实例，这个实例来决定当前的状态
> - 非具体状态
> 定义一个接口以封装与使用环境角色的一个特定状态相关的行为
> - 具体状态
> 实现状态角色定义的接口，并进行具体的相关操作

###### **设计问题**
> 实现具体的`自动售货机`系统

###### **问题分析**
> 1. 我们经常见到一些商品自动售货机，比如饮料自动售货机。当把若干元硬币投入饮料售货机，就会获得自己想要的饮料。
> 2. 饮料自动售货机一共有四种状态：
> - SoldState // 售出状态
> - SoleOutState // 售完状态
> - NoMoneyState // 未投币状态
> - HasMoneyState //投币状态
> 3. 根据具体情况，自动售货机目前可能有四个方法： 
> - insertMoney(); // 投币
> - backMoney(); // 退币
> - turnCrank(); // 转动曲柄，确认
> - dispense(); // 出货

###### **UML状态图**
![](http://homework.0x1010.com//screenshot/designpatterns/05-01.png)

###### **初级设计方案**
> 1. 先事先定义好每一个状态的状态值
> 2. 对每一个状态攥写一个函数块，对每个函数块用`switch`来切换各个状态值
> 3. 具体的代码实现如下
```java
/**
 * 自动售货机命令行（无设计模式）
 */
public class VendingMachine {

	// 已投币
	private final static int HAS_MONEY = 0;
	
	// 未投币
	private final static int NO_MONEY = 1;
	
	// 售出商品
	private final static int SOLD = 2;
	
	// 商品售罄
	private final static int SOLD_OUT = 3;

	// 当前状态
	private int currentStatus = NO_MONEY;
	
	// 商品数量
	private int count = 0;

	public VendingMachine(int count) {
		this.count = count;
		if (count > 0)
			currentStatus = NO_MONEY;
	}

	/**
	 * 投入硬币，任何状态用户都可能投币
	 */
	public void insertMoney() {
		switch (currentStatus) {
			case NO_MONEY:
				currentStatus = HAS_MONEY;
				System.out.println("成功投入硬币");
				break;
			case HAS_MONEY:
				System.out.println("已经有硬币，无需投币");
				break;
			case SOLD:
				System.out.println("请稍等...");
				break;
			case SOLD_OUT:
				System.out.println("商品已经售罄，请勿投币");
				break;
		}
	}

	/**
	 * 退币，任何状态用户都可能退币
	 */
	public void backMoney() {
		switch (currentStatus) {
			case NO_MONEY:
				System.out.println("您未投入硬币");
				break;
			case HAS_MONEY:
				currentStatus = NO_MONEY;
				System.out.println("退币成功");
				break;
			case SOLD:
				System.out.println("您买了该饮料...");
				break;
			case SOLD_OUT:
				System.out.println("您未投币...");
				break;
			}
	}

	/**
	 * 转动手柄购买,任何状态用户都可能转动手柄
	 */
	public void turnCrank() {
		switch (currentStatus) {
			case NO_MONEY:
				System.out.println("请先投入硬币");
				break;
			case HAS_MONEY:
				System.out.println("正在售出该饮料....");
				currentStatus = SOLD;
				dispense();
				break;
			case SOLD:
				System.out.println("连续转动也没用...");
				break;
			case SOLD_OUT:
				System.out.println("饮料已经售罄");
				break;
		}
	}

	/**
	 * 发放商品
	 */
	private void dispense() {
		switch (currentStatus) {
			case NO_MONEY:
			case HAS_MONEY:
			case SOLD_OUT:
				throw new IllegalStateException("非法的状态...");
			case SOLD:
				count--;
				System.out.println("售出该饮料...");
				if (count == 0) {
					System.out.println("该饮料已售罄");
					currentStatus = SOLD_OUT;
				} else {
					currentStatus = NO_MONEY;
				}
				break;
		}
	}
	
	public static void main(String[] args) {
		VendingMachine machine = new VendingMachine(10);  
        machine.insertMoney();
        machine.backMoney();
        machine.insertMoney();  
        machine.turnCrank();
        machine.insertMoney();  
        machine.insertMoney();  
        machine.turnCrank();
        machine.turnCrank();
        machine.backMoney();
        machine.turnCrank();
	}
}
```

###### **执行结果**
> 成功投入硬币
> 退币成功
> 成功投入硬币
> 正在售出该饮料....
> 售出该饮料...
> 成功投入硬币
> 已经有硬币，无需投币
> 正在售出该饮料....
> 售出该饮料...
> 请先投入硬币
> 您未投入硬币

###### **初级方案实现小结**
> 1. 功能性
>  - 基本实现了所需功能
> 2. 扩展性
> - 但需求发生变动时，有些事情是不可避免的 —— 修改各个状态的代码块

> 3. 冗余性
> - 代码冗余度高，都要为每个状态写一个`switch()`代码块

> 4. 缺陷
> - 如果在我们刚写的代码上直接添加，则需要在每个动作的`switch()`中添加判断条件，且非常容易出错，无法做到高内聚，低耦合。所以现在我们需要对代码进行重构

###### **优化设计方案，重构代码，使用`State`设计模式解决问题**
> - 状态抽象接口类`State`
```java
/**
 * 状态抽象接口
 */
public interface State {
	
	/**
	 * 投币
	 */
	public void insertMoney();

	/**
	 * 退币
	 */
	public void backMoney();

	/**
	 * 转动曲柄明确购买
	 */
	public void turnCrank();

	/**
	 * 售出商品
	 */
	public void dispense();
}
```

> - 已投币状态类`HasMoneyState`
```java
/**
 * 已投币状态
 */
public class HasMoneyState implements State {

	private VendingMachine machine;

	public HasMoneyState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		System.out.println("您又进行了投币");
	}

	@Override
	public void backMoney() {
		System.out.println("退币成功");
		machine.setState(machine.getNoMoneyState());
	}

	@Override
	public void turnCrank() {
		System.out.println("手柄已转动,且正在出货");
		if (machine.getCount() > 0) {
			machine.setState(machine.getSoldState());
		} else {
			machine.setState(machine.getSoldOutState());
		}
	}

	@Override
	public void dispense() {
		throw new IllegalStateException("非法状态！");
	}
}
```

> - 未投币状态类`NoMoneyState`
```java
/**
 * 未投币状态
 */
public class NoMoneyState implements State {

	private VendingMachine machine;

	public NoMoneyState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		System.out.println("投币成功");
		machine.setState(machine.getHasMoneyState());
	}

	@Override
	public void backMoney() {
		System.out.println("您未投币,请先投币");
	}

	@Override
	public void turnCrank() {
		System.out.println("您未投币,无法出售");
	}

	@Override
	public void dispense() {
		throw new IllegalStateException("非法状态！");
	}
}
```

> - 出售状态类`SoldState`
```java
/**
 * 出售状态
 */
public class SoldState implements State {

	private VendingMachine machine;

	public SoldState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		System.out.println("正在出货，请勿投币");
	}

	@Override
	public void backMoney() {
		System.out.println("退币成功");
		machine.setState(machine.getNoMoneyState());
	}

	@Override
	public void turnCrank() {
		System.out.println("正在出货，请勿重复转动手柄");
	}

	@Override
	public void dispense() {
		machine.dispense();
		if (machine.getCount() > 0) {
			machine.setState(machine.getNoMoneyState());
		} else {
			System.out.println("商品已经售罄");
			machine.setState(machine.getSoldOutState());
		}
	}
}
```

> - 售罄状态类`SoldOutState`
```java
/**
 * 售罄状态
 */
public class SoldOutState implements State {

	private VendingMachine machine;

	public SoldOutState(VendingMachine machine) {
		this.machine = machine;
	}

	@Override
	public void insertMoney() {
		System.out.println("投币成功");
		machine.setState(machine.getHasMoneyState());
	}

	@Override
	public void backMoney() {
		System.out.println("您未投币,请先投币");
	}

	@Override
	public void turnCrank() {
		System.out.println("商品已售罄");
	}

	@Override
	public void dispense() {
		throw new IllegalStateException("非法状态！");
	}
}
```

> - 自动售货机类`VendingMachine`
```java
/**
 * 自动售货机
 */
public class VendingMachine {
	
	private State noMoneyState;
	private State hasMoneyState;
	private State soldState;
	private State soldOutState;

	private int count = 0;
	private State currentState = noMoneyState;

	public VendingMachine(int count) {
		noMoneyState = new NoMoneyState(this);
		hasMoneyState = new HasMoneyState(this);
		soldState = new SoldState(this);
		soldOutState = new SoldOutState(this);
		if (count > 0) {
			this.count = count;
			currentState = noMoneyState;
		}
	}

	public void insertMoney() {
		currentState.insertMoney();
	}

	public void backMoney() {
		currentState.backMoney();
	}

	public void turnCrank() {
		currentState.turnCrank();
		if (currentState == soldState)
			currentState.dispense();
	}

	public void dispense() {
		System.out.println("售出一件饮料...");
		if (count != 0) {
			count -= 1;
		}
	}

	public int getCount() {
		return count;
	}

	public void setState(State state) {
		this.currentState = state;
	}

	public State getHasMoneyState() {
		return hasMoneyState;
	}

	public State getNoMoneyState() {
		return noMoneyState;
	}

	public State getSoldState() {
		return soldState;
	}

	public State getSoldOutState() {
		return soldOutState;
	}
}
```

> - 主程测试类`Main`
```java
public class Main {
	public static void main(String[] args) {
		VendingMachine machine = new VendingMachine(3);
		machine.insertMoney();
		machine.turnCrank();
		machine.backMoney();
		machine.insertMoney();
		machine.insertMoney();
		machine.turnCrank();
		machine.turnCrank();
		machine.insertMoney();
		machine.turnCrank();
		machine.turnCrank();
		machine.insertMoney();
		machine.backMoney();
	}
}
```

###### **使用State模式后的运行结果**
> 投币成功
> 手柄已转动,且正在出货
> 售出一件饮料...
> 您未投币,请先投币
> 投币成功
> 您又进行了投币
> 手柄已转动,且正在出货
> 售出一件饮料...
> 您未投币,无法出售
> 投币成功
> 手柄已转动,且正在出货
> 售出一件饮料...
> 商品已经售罄
> 商品已售罄
> 投币成功
> 退币成功

###### **UML类图**
![](http://homework.0x1010.com//screenshot/designpatterns/05-02.png)

###### **进一步重构，实现GUI化设计方案**
> 1. 将多个饮料的相关信息用`JSON`数据文件将其保存在`drink.json`文件中
> 2. 然后在工具类中用`ReadJson`类来解析`JSON`数据，并且实例化`Repository`类对象，将解析的结果都用一个`JavaBean`对象`Drink`保存在仓储中
> 3. 在`DrinkStorePanel`饮料商店面板类中对每一个饮料的图像按钮设置点击事件，根据不同的点击事件更改<kbd>自动售货机</kbd>不同的<kbd>状态</kbd>
> 4. UML类图如下所示
> ![](http://homework.0x1010.com/screenshot/designpatterns/05-03.png)

###### **运行结果及效果**
> - 运行结果
> ![](http://homework.0x1010.com/screenshot/designpatterns/05-04.png)
> ![](http://homework.0x1010.com/screenshot/designpatterns/05-05.png)

###### **组员分工情况**
> - 班级：软件工程专升本1502
> - 组员
> 1. 
> 姓名：戴剑锋（组长）
> 学号：201526740205
> 任务：文档攥写、讨论相关的具体设计、UML建模图
>  2. 
> 姓名：颜孙通
> 学号：201526740222
> 任务：PPT演讲、PPT完善与修改、使用State模式设计并且对GUI模块化代码实现、完善整理文档并用Markdown对文档重新排版、修订UML建模图
> 3. 
> 姓名：裘武炀
> 学号：201526740218
> 任务：收集相关资料、State设计模式思路整理、非State模式设计的代码实现、使用State模式设计的代码实现、PPT制作
> 4. 
> 姓名：杨鹤
> 学号：201526740223
> 任务：文档总结、讨论相关的具体设计、完善文档的攥写

###### **总结**
> - 适用情景
> 1. 一个对象的行为依赖于它的状态，并且它必须在运行时根据状态变化改变它的行为。
> 2. 需要编写大量的条件分支语句来决定一个操作的行为，而且这些条件恰好表现对象的一种状态。
> - 优点：
> 1. 使用一个类封装对象的一个状态，很容易增加新的状态。
> 2.在状态模式中，环境中不必出现大量的条件判断语句。环境实例所呈现的状态变得更加清晰、容易理解。
> 3. 使用状态模式可以让用户程序很方便地切换环境实例的状态。
> 4. 使用状态模式不会让环境的实例中出现内部状态不一致的情况。
> 5. 当状态对象没有实例变量时，环境的各个实例可以共享一个状态对象。