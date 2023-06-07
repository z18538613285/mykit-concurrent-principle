package io.gushizhao.concurrent.lab04;

import java.util.concurrent.*;

/**
 * @Author huzhichao
 * @Description 测试 Future 获取异步结果
 * @Date 2023/3/20 14:57
 *
 * 使用Future接口或者其实现类FutureTask来接收任务的返回结果。
 */
public class FutureTest {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> future = executorService.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "测试 Future 获取异步结果";
            }
        });
        System.out.println(future.get());
        executorService.shutdown();

    }
}
