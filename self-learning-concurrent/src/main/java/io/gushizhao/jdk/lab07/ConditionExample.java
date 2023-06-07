package io.gushizhao.jdk.lab07;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 10:00
 *
 * Condition
 * Condition是一个多线程间协调通信的工具类，Condition除了实现wait和notify的功能以外，它的好处在于一个lock可以创建多
 * 个Condition，可以选择性的通知wait的线程
 *
 * 特点：
 * 1、Condition 的前提是Lock，由AQS中newCondition()方法 创建Condition的对象
 * 2、Condition await方法表示线程从AQS中移除，并释放线程获取的锁，并进入Condition等待队列中等待，等待被signal
 * 3、Condition signal方法表示唤醒对应Condition等待队列中的线程节点，并加入AQS中，准备去获取锁。
 */
public class ConditionExample {

    private static Log log = LogFactory.get(ConditionExample.class);

    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        Condition condition = reentrantLock.newCondition();

        new Thread(() -> {
            try {
                reentrantLock.lock();
                log.info("wait signal"); //1
                condition.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("get signal"); //4
            reentrantLock.unlock();
        }).start();

        new Thread(() -> {
            reentrantLock.lock();
            log.info("get lock"); //2
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            condition.signalAll();
            log.info("send signal ~"); //3
            reentrantLock.unlock();
        }).start();


    }
}
