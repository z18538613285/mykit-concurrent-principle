package io.gushizhao.concurrent.lab12;

import jdk.nashorn.internal.ir.Block;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.IntStream;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 11:13
 */
public class ThreadPool {

    // 默认阻塞队列大小
    public static final int DEFAULT_WORKQUEUE_SIZE = 5;

    // 模拟实际的线程池使用的阻塞队列实现 生产者-消费者模式
    private BlockingQueue<Runnable> workQueue;

    // 模拟实际的线程池使用的 List 集合保存线程池内部的工作线程
    private List<workThread> workThreads = new ArrayList<>();


    // 在 ThreadPool 的构造方法中传入线程池的大小
    public ThreadPool(int poolSize) {
        this(poolSize, new LinkedBlockingQueue<>(DEFAULT_WORKQUEUE_SIZE));
    }

    // 在 ThreadPool 的构造方法中传入线程池的大小和阻塞队列
    public ThreadPool(int poolSize, BlockingQueue<Runnable> workQueue) {
        this.workQueue = workQueue;
        // 创建 poolSize 个工作线程并将其加入到 workThreads 集合中
        IntStream.range(0, poolSize).forEach((i) -> {
            workThread workThread = new workThread();
            workThread.start();
            workThreads.add(workThread);
        });
    }


    // 通过线程池执行任务
    public void execute(Runnable task) {
        try {
            workQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //内部类WorkThread，模拟线程池中的工作线程
    //主要的作用就是消费workQueue中的任务，并执行
    //由于工作线程需要不断从workQueue中获取任务，使用了while(true)循环不断尝试消费队列中的任务
    class workThread extends Thread {
        @Override
        public void run() {

            while (true) {
                //当没有任务时，会阻塞
                try {
                    Runnable workTask = workQueue.take();
                    workTask.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
