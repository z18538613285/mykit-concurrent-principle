package io.gushizhao.jdk.lab02;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.StampedLock;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 14:50
 *
 * JDK1.8中新增的StampedLock
 *
 * StampedLock是一种在读取共享变量的过程中，允许后面的一个线程获取写锁对共享变量进行写操作，使用乐观读避免
 * 数据不一致的问题，并且在读多写少的高并发环境下，比ReadWriteLock更快的一种锁。
 *
 * StampedLock三种锁模式
 *
 * ReadWriteLock支持两种锁模式：一种是读锁，另一种是写锁，并且ReadWriteLock允许多个线程同时读共享变量，
 * 在读时，不允许写，在写时，不允许读，读和写是互斥的，所以，ReadWriteLock中的读锁，更多的是指悲观读锁。
 *
 * StampedLock支持三种锁模式：写锁、读锁（这里的读锁指的是悲观读锁）和乐观读（很多资料和书籍写的是乐观读锁，这里我
 * 个人觉得更准确的是乐观读，为啥呢？我们继续往下看啊）。其中，写锁和读锁与ReadWriteLock中的语义类似，允许多个线程
 * 同时获取读锁，但是只允许一个线程获取写锁，写锁和读锁也是互斥的。
 *
 * 另一个与ReadWriteLock不同的地方在于：StampedLock在获取读锁或者写锁成功后，都会返回一个Long类型的变量，之后在
 * 释放锁时，需要传入这个Long类型的变量。例如，下面的伪代码所示的逻辑演示了StampedLock如何获取锁和释放锁。
 *
 * StampedLock实现思想
 * StampedLock内部是基于CLH锁实现的，CLH是一种自旋锁，能够保证没有“饥饿现象”的发生，并且能够保证FIFO（先进先出）
 * 的服务顺序。
 *
 * 在CLH中，锁维护一个等待线程队列，所有申请锁，但是没有成功的线程都会存入这个队列中，每一个节点代表一个线程，保存
 * 一个标记位(locked)，用于判断当前线程是否已经释放锁，当locked标记位为true时， 表示获取到锁，当locked标记位为false
 * 时，表示成功释放了锁。
 *
 * StampedLock的注意事项
 * 在读多写少的高并发环境下，StampedLock的性能确实不错，但是它不能够完全取代ReadWriteLock。在使用的时候，也需要特
 * 别注意以下几个方面。
 * 1、StampedLock不支持重入
 * 2、StampedLock不支持条件变量
 * 3、StampedLock使用不当会导致CPU飙升
 *
 * 所以，在使用StampedLock时，一定要注意避免线程所在的CPU飙升的问题。那如何避免呢？
 * 那就是使用StampedLock的readLock()方法或者读锁和使用writeLock()方法获取写锁时，一定不要调用线程的中断方法来中断线
 * 程，如果不可避免的要中断线程的话，一定要用StampedLock的readLockInterruptibly()方法获取可中断的读锁和使用
 * StampedLock的writeLockInterruptibly()方法获取可中断的悲观写锁。
 */
public class StampedLockDemo {

    private static Log log = LogFactory.get(StampedLockDemo.class);

    //请求总数
    public static int clientTotal = 5000;
    //同时并发执行的线程数
    public static int threadTotal = 200;
    public static int count = 0;
    private static final StampedLock lock = new StampedLock();

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
        //加锁时返回一个long类型的票据
        long stamp = lock.writeLock();
        try{
            count ++;
        }finally {
            //释放锁的时候带上加锁时返回的票据
            lock.unlock(stamp);
        }
    }

    // 创建 StampedLock 锁对向
    public StampedLock stampedLock = new StampedLock();

    // 获取、释放读锁
    public void testGetAndReleaseReadLock() {
        long stamp = stampedLock.readLock();
        try {
            // 执行获取读锁后的业务逻辑
        } finally {
            // 释放锁
            stampedLock.unlockRead(stamp);
        }
    }

    // 获取、释放写锁
    public void testGetAndReleaseWriteLock() {
        long stamp = stampedLock.writeLock();
        try {
            // 执行获取读锁后的业务逻辑
        } finally {
            // 释放锁
            stampedLock.unlockWrite(stamp);
        }
    }

    /**
     * StampedLock支持乐观读，这是它比ReadWriteLock性能要好的关键所在。 ReadWriteLock在读取共享变量时，所有对共享
     * 变量的写操作都会被阻塞。而StampedLock提供的乐观读，在多个线程读取共享变量时，允许一个线程对共享变量进行写操作。
     *
     * 看一下JDK官方给出的StampedLock示例
     */

    class Point {
        private double x, y;
        private final StampedLock sl = new StampedLock();

        void move(double deltaX, double deltaY) { // an exclusively locked method
            long stamp = sl.writeLock();
            try {
                x += deltaX;
                y += deltaY;
            } finally {
                sl.unlockWrite(stamp);
            }
        }

        /**
         * 这种将乐观读升级为悲观读锁的方式相比一直使用乐观读的方式更加合理，如果不升级为悲观读锁，则程序会在一个循环中反复
         * 执行乐观读操作，直到乐观读操作期间没有线程执行写操作，而在循环中不断的执行乐观读会消耗大量的CPU资源，升级为悲观
         * 读锁是更加合理的一种方式。
         * @return
         */
        // 如果在执行乐观读操作时，另外的线程对共享变量进行了写操作，则会把乐观读升级为悲观读锁，
        double distanceFromOrigin() { // A read-only method
            //乐观读
            long stamp = sl.tryOptimisticRead();
            double currentX = x, curretnY = y;
            //判断是否有线程对变量进行了写操作
            //如果有线程对共享变量进行了写操作
            //则sl.validate(stamp)会返回false
            if (!sl.validate(stamp)) { //检查发出乐观读锁后同时是否有其他写锁发生？
                //将乐观读升级为悲观读锁
                stamp = sl.readLock();
                try {
                    currentX = x;
                    curretnY = y;
                } finally {
                    //释放悲观锁
                    sl.unlockRead(stamp);
                }
            }
            return Math.sqrt(currentX * currentX + curretnY * curretnY);
        }

        // 下面是悲观读锁案例
        void moveIfAtOrigin(double newX, double newY) { // upgrade
            // Could instead start with optimistic, not read mode
            long stamp = sl.readLock();
            try {
                while (x == 0.0 && y == 0.0) {
                    long ws = sl.tryConvertToOptimisticRead(stamp); //将读锁转为写锁
                    if (ws != 0L) { //这是确认转为写锁是否成功
                        stamp = ws; //如果成功 替换票据
                        x = newX; //进行状态改变
                        y = newY; //进行状态改变
                        break;
                    } else { //如果不能成功转换为写锁
                        sl.unlockRead(stamp); //我们显式释放读锁
                        stamp = sl.writeLock(); //显式直接进行写锁 然后再通过循环再试
                    }
                }
            } finally {
                sl.unlock(stamp);  //释放读锁或写锁
            }
        }
    }


    public void testStampedLock() throws Exception {
        final StampedLock lock = new StampedLock();
        Thread thread01 = new Thread(() -> {
            // 获取读锁
            lock.writeLock();
            // 永远阻塞在此处， 不释放写锁
        });

        thread01.start();
        // 保证thread01 获取到写锁
        Thread.sleep(100);
        Thread thread02 = new Thread(() -> lock.readLock()); // 阻塞在悲观读

        thread02.start();
        // 保证thread02 获取到读锁
        Thread.sleep(100);
        // 中断线程 thread 02
        // 会导致 thread02 所在的 CPU 飙升
        thread02.interrupt();
        thread02.join();
    }

}
