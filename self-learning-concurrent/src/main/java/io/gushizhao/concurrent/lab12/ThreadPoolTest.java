package io.gushizhao.concurrent.lab12;

import java.util.stream.IntStream;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 11:30
 *
 * 测试线程池程序
 */
public class ThreadPoolTest {
    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(10);
        IntStream.range(0, 10).forEach(i -> {
            threadPool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "--->> Hello ThreadPool");
            });
        });
    }

}
