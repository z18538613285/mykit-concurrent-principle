package io.gushizhao.jdk.lab05;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 16:17
 *
 * 对象引用类型的原子类包含：AtomicReference、AtomicStampedReference和AtomicMarkableReference。
 *
 * 利用这些对象引用类型的原子类，可以实现对象引用更新的原子化。AtomicReference提供的原子化更新操作与基本数据类型的
 * 原子类提供的更新操作差不多，只不过AtomicReference提供的原子化操作常用于更新对象信息。
 * (需要特别注意的是：使用对象引用类型的原子类，要重点关注ABA问题。)
 *
 *
 * 好在AtomicStampedReference和AtomicMarkableReference这两个原子类解决了ABA问题。
 * AtomicStampedReference类中的compareAndSet的方法签名如下所示。
 * boolean compareAndSet(V expectedReference, V newReference, int expectedStamp, int newStamp)
 *
 * AtomicMarkableReference类中的compareAndSet的方法签名如下所示。
 * boolean compareAndSet(V expectedReference, V newReference, boolean expectedMark, boolean newMark)
 *可以看到，AtomicMarkableReference解决ABA问题的方案就更简单了，在compareAndSet方法中，新增了boolean类型的校验
 * 值。
 *
 */
public class TestAtomicReference {
}
