package io.gushizhao.jdk.lab05;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 16:20
 *
 * 对象属性类型的原子类包含：AtomicIntegerFieldUpdater、AtomicLongFieldUpdater和
 * AtomicReferenceFieldUpdater。
 *
 * 利用对象属性类型的原子类可以原子化的更新对象的属性。值得一提的是，这三个类的对象都是通过反射的方式生成的，
 *
 * 我们不难看出，在AtomicIntegerFieldUpdater、AtomicLongFieldUpdater和AtomicReferenceFieldUpdater三个类的
 * newUpdater()方法中，只有传递的Class信息，并没有传递对象的引用信息。如果要更新对象的属性，则一定要使用对象的引
 * 用，那对象的引用是在哪里传递的呢？
 * 其实，对象的引用是在真正调用原子操作的方法时传入的。这里，我们就以compareAndSet()方法为例，如
 * //AtomicIntegerFieldUpdater的compareAndSet()方法
 * compareAndSet(T obj, int expect, int update)
 * //AtomicLongFieldUpdater的compareAndSet()方法
 * compareAndSet(T obj, long expect, long update)
 * //AtomicReferenceFieldUpdater的compareAndSet()方法
 * compareAndSet(T obj, V expect, V update)
 *
 * 需要注意的是：使用AtomicIntegerFieldUpdater、AtomicLongFieldUpdater和AtomicReferenceFieldUpdater更
 * 新对象的属性时，对象属性必须是volatile类型的，只有这样才能保证可见性；如果对象属性不是volatile类型的，
 * newUpdater()方法会抛出IllegalArgumentException这个运行时异常。
 */
public class TestAtomicField {


    // AtomicIntegerFieldUpdater 的 newUpdater 方法
    public static <U> AtomicIntegerFieldUpdater<U> newUpdater1(Class<U> tclass, String fieldName) {
        return null;
    }
    // AtomicLongFieldUpdater 的 newUpdater 方法
    public static <U> AtomicLongFieldUpdater<U> newUpdater2(Class<U> tclass, String fieldName) {
        return null;
    }
    // AtomicReferenceFieldUpdater 的 newUpdater 方法
    public static <U, W> AtomicReferenceFieldUpdater<U, W> newUpdater3(Class<U> tclass, Class<W> vclass, String fieldName) {
        return null;
    }
}
