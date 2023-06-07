package io.gushizhao.basecase.lab04;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 15:05
 *
 * JDK1.6中String类的坑
 *
 * public String substring(int bedinIndex, int endIndex){
 * ...
 * return ((beginIndex == 0) && (endIndex == count)) ? this : new String(offset + beginIndex,
 * endIndex - beginIndex, value);
 * }
 * String(int offset, int count, char[] value){
 * this.value = value;
 * this.offset = offset;
 * this.count = count;
 * }
 *
 * 导致问题的罪魁祸首就是下面的一行代码。
 * this.value = value;
 *
 * 在JDK1.6中，使用 String 类的构造函数创建子字符串的时候，并不只是简单的拷贝所需要的对象，而是每次都会把整个value引
 * 用进来。如果原来的字符串比较大，即使这个字符串不再被应用，这个字符串所分配的内存也不会被释放。 这也是我经过长时间
 * 的分析代码得出的结论，确实是太坑了！！
 *
 * 在JDK1.8中，当我们需要一个子字符串的时候，substring 生成了一个新的字符串，这个字符串通过构造函数的
 * Arrays.copyOfRange 函数进行构造。这个是没啥问题。
 *
 *
 * JVM优化的目标就是：尽可能让对象都在新生代里分配和回收，尽量别让太多对象频繁进入老年代，避免频繁对老年代进行垃圾
 * 回收，同时给系统充足的内存大小，避免新生代频繁的进行垃圾回收。
 *
 * 并发问题的“幕后黑手”
 * 源头一，缓存导致的可见性问题
 * 可见性就是说一个线程对共享变量的修改，另一个线程能够立刻看到。
 * 由于只有一个CPU内核，线程A和线程B无论谁修改了CPU中的变量V，另一个线程读到的变量V一定是最新的值。
 *
 *
 *
 */
public class StringTest {

}
