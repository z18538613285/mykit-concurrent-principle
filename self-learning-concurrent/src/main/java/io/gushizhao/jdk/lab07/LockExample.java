package io.gushizhao.jdk.lab07;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 17:47
 *
 * AQS中的ReentrantLock、ReentrantReadWriteLock、StampedLock与Condition
 *
 * ReentrantLock
 *
 * Java中主要分为两类锁，一类是synchronized修饰的锁，另外一类就是J.U.C中提供的锁。J.U.C中提供的核心锁就是
 * ReentrantLock。
 *
 * ReentrantLock（可重入锁）与synchronized区别：
 * （1）可重入性
 * 二者都是同一个线程进入1次，锁的计数器就自增1，需要等到锁的计数器下降为0时，才能释放锁。
 * （2）锁的实现
 * synchronized是基于JVM实现的，而ReentrantLock是JDK实现的。
 * （3）性能的区别
 * synchronized优化之前性能比ReentrantLock差很多，但是自从synchronized引入了偏向锁，轻量级锁也就是自旋锁后，性能就
 * 差不多了。
 * （4）功能区别
 * 便利性
 * synchronized使用起来比较方便，并且由编译器保证加锁和释放锁；ReentrantLock需要手工声明加锁和释放锁，最好是在
 * finally代码块中声明释放锁。
 * 锁的灵活度和细粒度
 * 在这点上ReentrantLock会优于synchronized。
 *
 * ReentrantLock独有的功能
 * 1、ReentrantLock可指定是公平锁还是非公平锁。而synchronized只能是非公平锁。所谓的公平锁就是先等待的线程先获得锁。
 * 2、提供了一个Condition类，可以分组唤醒需要唤醒的线程。而synchronized只能随机唤醒一个线程，或者唤醒全部的线程
 * 3、提供能够中断等待锁的线程的机制，lock.lockInterruptibly()。ReentrantLock实现是一种自旋锁，通过循环调用CAS操作来
 * 实现加锁，性能上比较好是因为避免了使线程进入内核态的阻塞状态。
 *
 * synchronized能做的事情ReentrantLock都能做，而ReentrantLock有些能做的事情，synchronized不能做。
 * 在性能上，ReentrantLock不会比synchronized差。
 *
 * synchronized的优势
 * 1、不用手动释放锁，JVM自动处理，如果出现异常，JVM也会自动释放锁。
 * 2、JVM用synchronized进行管理锁定请求和释放时，JVM在生成线程转储时能够锁定信息，这些对调试非常有价值，因为它们
 * 能标识死锁或者其他异常行为的来源。而ReentrantLock只是普通的类，JVM不知道具体哪个线程拥有lock对象。
 * 3、synchronized可以在所有JVM版本中工作，ReentrantLock在某些1.5之前版本的JVM中可能不支持。
 *
 * ReentrantLock中的部分方法说明
 * boolean tryLock():仅在调用时锁定未被另一个线程保持的情况下才获取锁定。
 * boolean tryLock(long, TimeUnit): 如果锁定在给定的等待时间内没有被另一个线程保持，且当前线程没有被中断，则获取这个锁定。
 * void lockInterruptibly():如果当前线程没有被中断，就获取锁定；如果被中断，就抛出异常。
 * boolean isLocked():查询此锁定是否由任意线程保持。
 * boolean isHeldByCurrentThread(): 查询当前线程是否保持锁定状态。
 * boolean isFair():判断是否是公平锁。
 * boolean hasQueuedThread(Thread)：查询指定线程是否在等待获取此锁定。
 * boolean hasQueuedThreads():查询是否有线程正在等待获取此锁定。
 * boolean getHoldCount():查询当前线程保持锁定的个数。
 *
 *
 *
 *
 *
 */
public class LockExample {

    private static Log log = LogFactory.get(LockExample.class);
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    public static int count = 0;
    private static final Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final Semaphore semaphore = new Semaphore(threadTotal);
        final CountDownLatch countDownLatch = new CountDownLatch(clientTotal);
        for(int i = 0; i < clientTotal; i++){
            executorService.execute(() -> {
                try{
                    semaphore.acquire();
                    add();
                    semaphore.release();
                }catch (Exception e){
                    log.error("exception", e);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        log.info("count:{}", count);
    }
    private static void add(){
        lock.lock();
        try{
            count ++;
        }finally {
            lock.unlock();
        }
    }

}
