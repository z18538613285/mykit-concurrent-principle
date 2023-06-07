package io.gushizhao.concurrent.lab09;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/21 15:21
 */
public class TimerTest {
    public static void main(String[] args) throws InterruptedException{
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println("测试 Timer 类");
            }
        }, 1000, 1000);

        Thread.sleep(10000);
        timer.cancel();
    }
}
