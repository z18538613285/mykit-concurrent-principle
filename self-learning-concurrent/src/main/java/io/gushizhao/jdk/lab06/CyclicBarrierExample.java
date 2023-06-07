package io.gushizhao.jdk.lab06;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.concurrent.*;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 17:28
 *
 * CyclicBarrier
 * 是一个同步辅助类，允许一组线程相互等待，直到到达某个公共的屏障点，通过它可以完成多个线程之间相互等待，只有当每个
 * 线程都准备就绪后，才能各自继续往下执行后面的操作。
 * 与CountDownLatch有相似的地方，都是使用计数器实现，当某个线程调用了CyclicBarrier的await()方法后，该线程就进入了等
 * 待状态，而且计数器执行加1操作，当计数器的值达到了设置的初始值，调用await()方法进入等待状态的线程会被唤醒，继续执
 * 行各自后续的操作。CyclicBarrier在释放等待线程后可以重用，所以，CyclicBarrier又被称为循环屏障。
 *
 * 使用场景
 * 可以用于多线程计算数据，最后合并计算结果的场景
 *
 * CyclicBarrier与CountDownLatch的区别
 * 1、CountDownLatch的计数器只能使用一次，而CyclicBarrier的计数器可以使用reset()方法进行重置，并且可以循环使用
 * 2、CountDownLatch主要实现1个或n个线程需要等待其他线程完成某项操作之后，才能继续往下执行，描述的是1个或n个线
 * 程等待其他线程的关系。而CyclicBarrier主要实现了多个线程之间相互等待，直到所有的线程都满足了条件之后，才能继续
 * 执行后续的操作，描述的是各个线程内部相互等待的关系。
 * 3、CyclicBarrier能够处理更复杂的场景，如果计算发生错误，可以重置计数器让线程重新执行一次。
 * 4、CyclicBarrier中提供了很多有用的方法，比如：可以通过getNumberWaiting()方法获取阻塞的线程数量，通过isBroken()方
 * 法判断阻塞的线程是否被中断。
 *
 *
 *
 */
public class CyclicBarrierExample {
    private static Log log = LogFactory.get(CyclicBarrierExample.class);

    //private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    // 在声明CyclicBarrier的时候，还可以指定一个Runnable，当线程达到屏障的时候，可以优先执行Runnable中的方法。
    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> log.info("callback is running"));

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++){
            final int threadNum = i;
            Thread.sleep(1000);
            executorService.execute(() -> {
                try {
                    race(threadNum);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        executorService.shutdown();
    }
    private static void race(int threadNum) throws Exception{
        Thread.sleep(1000);
        log.info("{} is ready", threadNum);
        cyclicBarrier.await();

        //设置等待超时示例代码
        //try{
        //    cyclicBarrier.await(2000, TimeUnit.MILLISECONDS);
        //}catch (BrokenBarrierException | TimeoutException e){
        //    log.warn("BarrierException", e);
        //}
        log.info("{} continue", threadNum);
    }
}
