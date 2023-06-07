package io.gushizhao.concurrent.lab06;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Author huzhichao
 * @Description 解决SimpleDateFormat类的线程安全问题
 * @Date 2023/3/20 16:10
 *
 *
 * 解决SimpleDateFormat类的线程安全问题
 * 1、ThreadLocal 方式
 * 使用ThreadLocal存储每个线程拥有的SimpleDateFormat对象的副本，能够有效的避免多线程造成的线程安全问题，
 *
 * 此种方式运行效率比较高，推荐在高并发业务场景的生产环境使用。
 */
public class SimpleDataFormatTest05 {

    private static final int EXECUTE_COUNT = 1000;

    private static final int THREAD_COUNT = 20;

    private static ThreadLocal<DateFormat> threadLocal = new ThreadLocal<DateFormat>(){
        @Override
        protected DateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    public static void main(String[] args) throws InterruptedException{
        // 可以理解为一个计数信号量，必须由获取它的线程释放，经常用来限制访问某些资源的线程数量，例如限流等
        final Semaphore semaphore = new Semaphore(THREAD_COUNT);
        // CountDownLatch类可以使一个线程等待其他线程各自执行完毕后再执行
        final CountDownLatch countDownLatch = new CountDownLatch(EXECUTE_COUNT);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < EXECUTE_COUNT; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                    try {
                        // 将每个线程使用的SimpleDateFormat副本保存在ThreadLocal中，各个线程在使用时互不干扰，从而解决了
                        //线程安全问题。
                        threadLocal.get().parse("2023-03-20");
                    } catch (ParseException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                        e.printStackTrace();
                        System.exit(1);
                    } catch (NumberFormatException e) {
                        System.out.println("线程：" + Thread.currentThread().getName() + " 格式化日期失败");
                         e.printStackTrace();
                        System.exit(1);
                    }
                    semaphore.release();
                } catch (InterruptedException e) {
                    System.out.println("信号量发生错误");
                    e.printStackTrace();
                    System.exit(1);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        executorService.shutdown();
        System.out.println("所有线程格式化日期成功");
    }
}
