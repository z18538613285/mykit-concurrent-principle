package io.gushizhao.jdk.lab01;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 14:36
 *
 * 读写锁的升降级
 * 关于锁的升降级，小伙伴们需要注意的是：在ReadWriteLock中，锁是不支持升级的，因为读锁还未释放时，此时获取写锁，
 * 就会导致写锁永久等待，相应的线程也会被阻塞而无法唤醒。
 *
 * 虽然不支持锁升级，但是ReadWriteLock支持锁降级，例如，我们来看看官方的ReentrantReadWriteLock示例，
 *
 * 数据同步问题
 * 数据库和缓存之间的数据同步
 *
 * 超时机制
 * 这个比较好理解，就是在向缓存写入数据的时候，给一个超时时间，当缓存超时后，缓存的数据会自动从缓存中移除，此时程序
 * 再次访问缓存时，由于缓存中不存在相应的数据，查询数据库得到数据后，再将数据写入缓存。（采用这种方案需要注意缓存的穿透问题，）
 *
 * 定时更新缓存
 * 这种方案是超时机制的增强版，在向缓存中写入数据的时候，同样给一个超时时间。与超时机制不同的是，在程序后台单独启动
 * 一个线程，定时查询数据库中的数据，然后将数据写入缓存中，这样能够在一定程度上避免缓存的穿透问题。
 *
 * 实时更新缓存
 * 这种方案能够做到数据库中的数据与缓存的数据是实时同步的，可以使用阿里开源的Canal框架实现MySQL数据库与缓存数据的
 * 实时同步。
 */
public class CachedData {

    Object data;
    volatile boolean cacheValid;
    final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

    void processCachedData() {
        rwl.readLock().lock();
        if (!cacheValid) {
            // Must release read local before acquiring write lock
            rwl.readLock().unlock();
            rwl.writeLock().lock();
            try {
                // Recheck state because another thread might have
                // acquired write lock and changed state before we did
                if (!cacheValid) {
                    data = "...";
                    cacheValid = true;
                }
                // Downgrade by acquiring read lock before releasing write lock
                rwl.readLock().lock();
            } finally {
                rwl.writeLock().unlock(); // Unlock write. still hold read
            }
        }

        try {
            //use(data);
        } finally {
            rwl.readLock().unlock();
        }
    }
}
