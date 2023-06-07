package io.gushizhao.jdk.lab05;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 16:14
 *
 * 基本数据类型的原子类
 * 基本数据类型的原子类包含：AtomicBoolean、AtomicInteger和AtomicLong。
 *
 * 原子化加1或减1操作
 * //原子化的i++
 * getAndIncrement()
 * //原子化的i--
 * getAndDecrement()
 * //原子化的++i
 * incrementAndGet()
 * //原子化的--i
 * decrementAndGet()
 *
 * 原子化增加指定的值
 * //当前值+=delta，返回+=前的值
 * getAndAdd(delta)
 * //当前值+=delta，返回+=后的值
 * addAndGet(delta)
 *
 * CAS操作
 * //CAS操作，返回原子化操作的结果是否成功
 * compareAndSet(expect, update)
 *
 * 接收函数计算结果
 * //结果数据可通过传入func函数来计算
 * getAndUpdate(func)
 * updateAndGet(func)
 * getAndAccumulate(x,func)
 * accumulateAndGet(x,func)
 */
public class TestAtomic {

    private AtomicLong count = new AtomicLong(0);

    /**
     * 使用原子类实现count+1
     * 在并发编程领域，一个经典的问题就是count+1问题。也就是在高并发环境下，如何保证count+1的正确性。一种方案就是在临
     * 界区加锁来保护共享变量count，但是这种方式太消耗性能了。
     * 如果使用Java提供的原子类来解决高并发环境下count+的问题，则性能会大幅度提升。
     *
     * 可以看到，原子类实现count+1问题，既没有使用synchronized锁，也没有使用Lock锁。
     * 从本质上讲，它使用的是无锁或者是乐观锁方案解决的count+问题，说的具体一点就是CAS操作。
     * @param number
     */
    public void incrementCountBynumber(int number) {
        for (int i = 0; i < number; i++) {
            count.getAndIncrement();
        }
    }

    /**
     * CAS原理
     * CAS操作包括三个操作数：需要读写的内存位置(V)、预期原值(A)、新值(B)。如果内存位置与预期原值的A相匹配，那么将内存位
     * 置的值更新为新值B。
     * 如果内存位置与预期原值的值不匹配，那么处理器不会做任何操作。
     * 无论哪种情况，它都会在 CAS 指令之前返回该位置的值。（在 CAS 的一些特殊情况下将仅返回 CAS 是否成功，而不提取当前
     * 值。）
     * 简单点理解就是：位置 V 应该包含值 A；如果包含该值，则将 B 放到这个位置；否则，不要更改该位置，只返回位置V现在的
     * 值。这其实和乐观锁的冲突检测+数据更新的原理是一样的。
     */

    /**
     * ABA问题
     * 因为CAS需要在操作值的时候检查下值有没有发生变化，如果没有发生变化则更新，但是如果一个值原来是A，变成了B，又变成
     * 了A，那么使用CAS进行检查时会发现它的值没有发生变化，但是实际上却变化了。
     * ABA问题的解决思路就是使用版本号。在变量前面追加上版本号，每次变量更新的时候把版本号加1，那么A－B－A 就会变成1A-
     * 2B-3A。
     * 从Java1.5开始JDK的atomic包里提供的AtomicStampedReference类和AtomicMarkableReference类能够解决CAS的ABA问题。
     *
     */
}
