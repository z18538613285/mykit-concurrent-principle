package io.gushizhao.jdk.lab04;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 15:51
 *
 * 并发容器中的List相对来说比较简单，就一个CopyOnWriteArrayList。大家可以从字面的意思中就能够体会到：CopyOnWrite，
 * 在写的时候进行复制操作，也就是说在进行写操作时，会将共享变量复制一份。那这样做有什么好处呢？最大的好处就是：读操
 * 作可以做到完全无锁化。
 *
 * 如果在遍历CopyOnWriteArrayList时发生写操作，例如，向数组中增加一个元素时，CopyOnWriteArrayList则会将内部的数组
 * 复制一份出来，然后会在新复制出来的数组上添加新的元素，添加完再将array指向新的数组，如
 *
 * 对于CopyOnWriteArrayList的其他写操作和添加元素的操作原理相同，这里就不再赘述了。
 * 使用CopyOnWriteArrayList时需要注意的是：
 * CopyOnWriteArrayList只适合写操作比较少的场景，并且能够容忍读写操作在短时间内的不一致。
 * CopyOnWriteArrayList的迭代器是只读的，不支持写操作。
 */
public class TestList {
}
