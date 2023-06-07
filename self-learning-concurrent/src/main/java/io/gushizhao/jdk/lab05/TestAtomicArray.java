package io.gushizhao.jdk.lab05;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 16:29
 *
 * 数组类型的原子类包含：AtomicIntegerArray、AtomicLongArray和AtomicReferenceArray。
 * 利用数组类型的原子类可以原子化的更新数组里面的每一个元素，使用起来也非常简单，数组类型的原子类提供的原子化方法仅
 * 仅是在基本数据类型的原子类和对象引用类型的原子类提供的原子化方法的基础上增加了一个数组的索引参数。
 * 例如，我们以compareAndSet()方法为例，如下所示
 * //AtomicIntegerArray的compareAndSet()方法
 * compareAndSet(int i, int expect, int update)
 * //AtomicLongArray的compareAndSet()方法
 * compareAndSet(int i, long expect, long update)
 * //AtomicReferenceArray的compareAndSet()方法
 * compareAndSet(int i, E expect, E update)
 *
 *
 */
public class TestAtomicArray {
}
