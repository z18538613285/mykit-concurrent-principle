package io.gushizhao.jdk.lab01;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 14:07
 *
 * 读写锁
 * 一个共享变量允许同时被多个读线程读取到。
 * 一个共享变量在同一时刻只能被一个写线程进行写操作。
 * 一个共享变量在被写线程执行写操作时，此时这个共享变量不能被读线程执行读操作。
 *
 * 读写锁和互斥锁的一个重要的区别就是：读写锁允许多个线程同时读共享变量，而互斥锁不允
 * 许。所以，在高并发场景下，读写锁的性能要高于互斥锁。但是，读写锁的写操作是互斥的，也就是说，使用读写锁时，一个共
 * 享变量在被写线程执行写操作时，此时这个共享变量不能被读线程执行读操作。
 *
 * 读写锁支持公平模式和非公平模式，具体是在 ReentrantReadWriteLock 的构造方法中传递一个boolean类型的变量来控制。
 *
 * 需要注意的一点是：在读写锁中，读锁调用newCondition()会抛出UnsupportedOperationException异常，也就是
 * 说：读锁不支持条件变量。
 */
public class ReentrantReadWriteLockTest {
}
