#### ***IO示例***

###### **设计问题**
> 在论坛中为了防止用户发表不雅言论，常常需要将某些敏感词汇屏蔽。假设某论坛是将发帖内容保存在磁盘文件中，请利用装饰模式的概念编写一个过滤流类`ReplaceWriter`，使用该类的`Write`字符串系列方法会将敏感词汇用“*”替换掉。需要替换的敏感词汇保存在文件badwords.txt中。请画出相应类图，给出`ReplaceWriter`和测试主类的源代码。（提示：参考`FilterWriter`)

###### **设计方案**
> 1. `ReplaceWriter`类继承`Writer`，并重写`write()`方法，在方法中实现敏感词的替换，然后在调用`write()`输出写入到文件中
> 2. `Main`测试类中把`FileWrite`对象作为被装饰者，被`ReplaceWriter`类装饰
> 2. UML类图如下所示
> ![](http://homework.0x1010.com/screenshot/designpatterns/04-01.png)

###### **运行结果及效果分析**

- 样例输入
> 你是不是傻逼，自古二楼出傻逼

- 样例输出
> 你是不是\*，自古二楼出\*
- 运行结果
![](http://homework.0x1010.com/screenshot/designpatterns/04-02.png)

###### **主要核心代码**
**`write()` 方法**
```java
/**
 * 重写父类的方法并且替换敏感词，然后再写入到文件
 */
@Override
public void write(char[] cbuf, int off, int len) throws IOException {
	String content = "";
	for (int i = 0; i < cbuf.length; i++)
		content += cbuf[i];
	for(String word : badWords)
        if(content.contains(word))
            content = content.replaceAll(word, "*");
	out.write(content);
}
```
