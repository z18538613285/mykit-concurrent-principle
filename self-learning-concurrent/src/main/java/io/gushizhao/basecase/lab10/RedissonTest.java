package io.gushizhao.basecase.lab10;

import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.*;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/24 17:33
 *
 * Redisson框架十分强大，基于Redisson框架可以实现几乎你能想到的所有类型的分布式锁。这里，我就列举几个类型的分布式
 * 锁，并各自给出一个示例程序来加深大家的理解。
 */
public class RedissonTest {

    // 可重入锁（Reentrant Lock）
    // Redisson的分布式可重入锁RLock Java对象实现了java.util.concurrent.locks.Lock接口，同时还支持自动过期解锁。
    public void testReentrantLock(RedissonClient redissonClient) {
        RLock lock = redissonClient.getLock("anyLock");
        try {
            // 1、最常见的使用方法
            //lock.lock();
            // 2、支持过期解锁功能，10秒钟后自动解锁，无需调用 unlock 方法手动解锁
            //lock.lock(10, TimeUnit.SECONDS);
            // 3、尝试加锁，最多等待3秒，上锁以后 10 秒后自动解锁
            boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
            if (res) { // 成功
                // do your business
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // Redisson同时还为分布式锁提供了异步执行的相关方法：
    public void testAsyncReentrantLock(RedissonClient redissonClient) {
        RLock lock = redissonClient.getLock("anyLock");
        try {
            lock.lockAsync();
            lock.lockAsync(10, TimeUnit.SECONDS);
            Future<Boolean> res = lock.tryLockAsync(3, 10, TimeUnit.SECONDS);
            if (res.get()) { // 成功
                // do your business
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 公平锁（Fair Lock）
    // Redisson分布式可重入公平锁也是实现了java.util.concurrent.locks.Lock接口的一种RLock对象。在提供了自动过期解锁功能的
    //同时，保证了当多个Redisson客户端线程同时请求加锁时，优先分配给先发出请求的线程。
    public void testFairLock(RedissonClient redissonClient) {
        RLock fairLock = redissonClient.getLock("anyLock");
        try {
            // 最常见的使用功能方法
            fairLock.lock();
            fairLock.lock(10, TimeUnit.SECONDS);
            Boolean res = fairLock.tryLock(3, 10, TimeUnit.SECONDS);

            // Redisson同时还为分布式可重入公平锁提供了异步执行的相关方法：
            // RLock fairLock = redisson.getFairLock("anyLock");
            // fairLock.lockAsync();
            // fairLock.lockAsync(10, TimeUnit.SECONDS);
            // Future<Boolean> res = fairLock.tryLockAsync(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }
    }

    // 联锁（MultiLock）
    // Redisson的RedissonMultiLock对象可以将多个RLock对象关联为一个联锁，每个RLock对象实例可以来自于不同的Redisson实例。
    public void testMultiLock(RedissonClient redissonClient1, RedissonClient redissonClient2, RedissonClient redissonClient3) {
        RLock lock1 = redissonClient1.getLock("anyLock");
        RLock lock2 = redissonClient2.getLock("anyLock");
        RLock lock3 = redissonClient3.getLock("anyLock");
        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);
        try {
            // 同时加锁：lock1 lock2 lock3 ，所有的锁都上锁成功才算成功
            lock.lock();
            boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 红锁（RedLock）
    // Redisson的RedissonRedLock对象实现了Redlock介绍的加锁算法。该对象也可以用来将多个RLock对象关联为一个红锁，每个
    //RLock对象实例可以来自于不同的Redisson实例。
    public void testRedLock(RedissonClient redissonClient1, RedissonClient redissonClient2, RedissonClient redissonClient3) {
        RLock lock1 = redissonClient1.getLock("anyLock");
        RLock lock2 = redissonClient2.getLock("anyLock");
        RLock lock3 = redissonClient3.getLock("anyLock");
        RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
        try {
            // 同时加锁：lock1 lock2 lock3 ，红锁在大部分节点上加锁成功就算成功
            lock.lock();
            boolean res = lock.tryLock(3, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 读写锁（ReadWriteLock）
    // Redisson的分布式可重入读写锁RReadWriteLock,Java对象实现了java.util.concurrent.locks.ReadWriteLock接口。同时还支持
    //自动过期解锁。该对象允许同时有多个读取锁，但是最多只能有一个写入锁。
    public void testReadWriteLock(RedissonClient redissonClient) {
        RReadWriteLock rwlock = redissonClient.getReadWriteLock("anyRWLock");
        try {
            rwlock.readLock().lock();
            rwlock.writeLock().lock();
            rwlock.readLock().lock(10, TimeUnit.SECONDS);
            rwlock.writeLock().lock(10, TimeUnit.SECONDS);
            rwlock.readLock().tryLock(100, 10, TimeUnit.SECONDS);
            rwlock.writeLock().tryLock(100, 10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            rwlock.readLock().unlock();
            rwlock.writeLock().unlock();
        }
    }

    // 信号量 （Semaphore）
    // Redisson的分布式信号量（Semaphore）Java对象RSemaphore采用了与java.util.concurrent.Semaphore相似的接口和用法。
    public void testSemaphore(RedissonClient redissonClient) {
        RSemaphore semaphore = redissonClient.getSemaphore("semaphore");
        try {
            semaphore.acquire();
            // 或
            semaphore.acquireAsync();
            semaphore.acquire(23);
            semaphore.tryAcquire();
            // 或
            semaphore.tryAcquireAsync();
            semaphore.tryAcquire(23, TimeUnit.SECONDS);
            // 或
            semaphore.tryAcquireAsync(23, TimeUnit.SECONDS);
            semaphore.release(10);
            semaphore.release();
            // 或
            semaphore.releaseAsync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 信号量
    // Redisson的可过期性信号量（PermitExpirableSemaphore）实在RSemaphore对象的基础上，为每个信号增加了一个过期时
    //间。每个信号可以通过独立的ID来辨识，释放时只能通过提交这个ID才能释放。
    public void testPermitExpirableSemaphore(RedissonClient redissonClient) {
        RPermitExpirableSemaphore semaphore = redissonClient.getPermitExpirableSemaphore("semaphore");
        try {
            String permitId = semaphore.acquire();
            // 获取一个信号，有效期只有 2 秒钟
            String acquire = semaphore.acquire(2, TimeUnit.SECONDS);
            //...
            semaphore.release(acquire);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    // 闭锁（CountDownLatch）
    // Redisson的分布式闭锁（CountDownLatch）Java对象RCountDownLatch采用了与java.util.concurrent.CountDownLatch相似
    // 的接口和用法。
    public void testCountDownLatch(RedissonClient redissonClient) {
        RCountDownLatch latch = redissonClient.getCountDownLatch("anyCountDownLatch");
        try {
            latch.trySetCount(1);
            latch.await();
            // 在其它线程或其它 JVM 里
            RCountDownLatch otherLatch = redissonClient.getCountDownLatch("anyCountDownLatch");
            otherLatch.countDown();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
