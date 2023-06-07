package io.gushizhao.jdk.lab06;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import cn.hutool.log.dialect.log4j.Log4jLog;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 16:37
 * CountDownLatch
 * 同步辅助类，通过它可以阻塞当前线程。也就是说，能够实现一个线程或者多个线程一直等待，直到其他线程执行的操作完成。
 * 使用一个给定的计数器进行初始化，该计数器的操作是原子操作，即同时只能有一个线程操作该计数器。
 * 调用该类await()方法的线程会一直阻塞，直到其他线程调用该类的countDown()方法，使当前计数器的值变为0为止。每次调用
 * 该类的countDown()方法，当前计数器的值就会减1。当计数器的值减为0的时候，所有因调用await()方法而处于等待状态的线程
 * 就会继续往下执行。这种操作只能出现一次，因为该类中的计数器不能被重置。如果需要一个可以重置计数次数的版本，可以考
 * 虑使用CyclicBarrier类。
 *
 * CountDownLatch支持给定时间的等待，超过一定的时间不再等待，使用时只需要在countDown()方法中传入需要等待的时间即
 * 可。此时，countDown()方法的方法签名如下：
 * public boolean await(long timeout, TimeUnit unit)
 *
 * 使用场景
 * 在某些业务场景中，程序执行需要等待某个条件完成后才能继续执行后续的操作。典型的应用为并行计算：当某个处理的运算量
 * 很大时，可以将该运算任务拆分成多个子任务，等待所有的子任务都完成之后，父任务再拿到所有子任务的运算结果进行汇总。
 */

public class CountDownLatchExample {

    // 示例代码
    // 调用ExecutorService类的shutdown()方法，并不会第一时间内把所有线程全部都销毁掉，而是让当前已有的线程全部执行完，
    //之后，再把线程池销毁掉。

    public static final int threadCount = 200;

    private static Log log = LogFactory.get(CountDownLatchExample.class);

    public static void main(String[] args) throws InterruptedException{
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
        for (int i = 0; i < threadCount; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        //支持给定时间等待的示例
        countDownLatch.await(10, TimeUnit.MICROSECONDS);
        log.info("finish");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        Thread.sleep(100);
        log.info("{}", threadNum);
        Thread.sleep(100);
    }


}
