package io.gushizhao.basecase.lab11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/27 10:35
 *
 * 用Java实现线程的等待与通知机制
 *
 * 这里，需要注意如下事项：
 * （1）使用notify()和notifyAll()方法通知线程时，调用notify()和notifyAll()方法时，满足线程的执行条件，但是当线程真正执
 * 行的时候，条件可能已经不再满足了，可能有其他线程已经进入临界区执行。
 * （2）被通知的线程继续执行时，需要先获取互斥锁，因为在调用wait()方法等待时已经释放了互斥锁。
 * （3）wait()、notify()和notifyAll()方法操作的队列是互斥锁的等待队列，如果synchronized锁定的是this对象，则一定要使
 * 用this.wait()、this.notify()和this.notifyAll()方法；如果synchronized锁定的是target对象，则一定要使用target.wait()、
 * target.notify()和target.notifyAll()方法。
 * （4）wait()、notify()和notifyAll()方法调用的前提是已经获取了相应的互斥锁，也就是说，wait()、notify()和notifyAll()方
 * 法都是在synchronized方法中或代码块中调用的。如果在synchronized方法外或代码块外调用了三个方法，或者锁定的对象是
 * this，使用target对象调用三个方法的话，JVM会抛出java.lang.IllegalMonitorStateException异常。
 *
 * 那么，问题来了！为何是在while循环中调用wait()方法呢？因为当wait()方法返回时，有可能线程执行的条件已经改变，也就是
 * 说，之前条件是满足的，但是现在已经不满足了，所以要重新检验条件是否满足。
 *
 * notify()和notifyAll()的区别
 * notify()方法
 * 随机通知等待队列中的一个线程。
 * notifyAll()方法
 * 通知等待队列中的所有线程。
 * 在实际工作过程中，如果没有特殊的要求，尽量使用notifyAll()方法。因为使用notify()方法是有风险的，可能会导致某些线程
 * 永久不会被通知到！
 */
public class TransferAccountBetter {

/*

    public class ResourceRequester {
        // 存放申请资源的集合
        private List<Object> resources = new ArrayList<>();

        // 一次申请所有的资源
        public synchronized void applyResources(Object source, Object target) {
            while (resources.contains(source) || resources.contains(target)) {
                try {
                    wait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            resources.add(source);
            resources.add(target);
        }

        // 释放资源
        public synchronized void releaseResource(Object source, Object target) {
            resources.remove(source);
            resources.remove(target);
            notifyAll();
        }
    }

    // 生成ResourcesRequester单例对象的Holder类ResourcesRequesterHolder的代码如下所示。
    public  class ResourcesRequesterHolder {
        private ResourcesRequesterHolder(){}
        public static ResourceRequester getInstance() {
            return Singleton.INSTANCE.getSingleton();
        }
        private enum Singleton {
            INSTANCE;
            private ResourceRequester singleton;
            Singleton() {
                singleton = new ResourceRequester();
            }
            public ResourceRequester getSingleton() {
                return singleton;
            }
        }
    }

    private Integer balance;
    // 此时，TansferAccount类的代码如下所示。
    private ResourceRequester resourceRequester;

    public TransferAccountBetter(Integer balance) {
        this.balance = balance;
        this.resourceRequester = ResourcesRequesterHolder.getInstance();
    }

    public void transfer(TransferAccountBetter target, Integer transferMoney) {
        // 一次申请转出账户和转入账户，直到成功
        resourceRequester.applyResources(this, target);

        try {
            // 对转出账户加锁
            synchronized (this) {
                // 对转入账户加锁
                synchronized (target) {
                    if (this.balance >= transferMoney) {
                        this.balance -= transferMoney;
                        target.balance += transferMoney;
                    }
                }
            }
        } finally {
            // 最后释放账户资源
            resourceRequester.releaseResource(this, target);
        }

    }*/
}

