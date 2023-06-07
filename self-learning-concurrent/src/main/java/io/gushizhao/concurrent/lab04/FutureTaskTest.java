package io.gushizhao.concurrent.lab04;

import java.util.concurrent.*;

/**
 * @Author huzhichao
 * @Description 使用FutureTask类获取异步结果
 * @Date 2023/3/20 15:01
 */
public class FutureTaskTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // 使用FutureTask类获取异步结果
        FutureTask<String> futureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "测试 FutureTask 获取异步结果";
            }
        });
        new Thread(futureTask).start();
        System.out.println(futureTask.get());


        ExecutorService executorService = Executors.newSingleThreadExecutor();
        FutureTask<String> stringFutureTask = new FutureTask<>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "测试 FutureTask 获取异步结果";
            }
        });
        executorService.execute(stringFutureTask);
        System.out.println(futureTask.get());
        executorService.shutdown();
    }
}
