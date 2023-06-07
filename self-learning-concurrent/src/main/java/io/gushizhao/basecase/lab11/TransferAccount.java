package io.gushizhao.basecase.lab11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/27 10:35
 */
public class TransferAccount {

    private Integer balance;

    public void transfer(TransferAccount target, Integer transferMoney) {
        synchronized (TransferAccount.class) {
            if (this.balance >= transferMoney) {
                this.balance -= transferMoney;
                target.balance += transferMoney;
            }
        }
    }
    /**
     * 这种方式确实解决了转账操作的并发问题，但是这种方式在高并发环境下真的可取吗？试想，如果我们在高并发环境下使用上述
     * 代码来处理转账操作，因为TansferAccount.class对象是JVM在加载TansferAccount类的时候创建的，所有的TansferAccount实
     * 例对象都会共享一个TansferAccount.class对象。也就是说，所有TansferAccount实例对象执行transfer()方法时，都是互斥
     * 的！！换句话说，所有的转账操作都是串行的！！
     *
     * 如果所有的转账操作都是串行执行的话，造成的后果就是：账户A为账户B转账完成后，才能进行账户C为账户D的转账操作。如
     * 果全世界的网民一起执行转账操作的话，这些转账操作都串行执行，那么，程序的性能是完全无法接受的！！！
     * 其实，账户A为账户B转账的操作和账户C为账户D转账的操作完全可以并行执行。所以，我们必须优化加锁方式，提升程序的性
     * 能！！
     *
     */

    /**
     * 仔细分析下上面的代码业务，上述代码的转账操作中，涉及到转出账户this和转入账户target，所以，我们可以分别对转出账户
     * this和转入账户target加锁，只有两个账户加锁都成功时，才执行转账操作。这样就能够做到账户A为账户B转账的操作和账户C
     * 为账户D转账的操作完全可以并行执行。
     */
    public void transfer2(TransferAccount target, Integer transferMoney) {
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
    }
    /**
     * 此时，上面的代码看上去没啥问题，但真的是这样吗？ 我也希望程序是完美的，但是往往却不是我们想的那样啊！没错，上面的
     * 程序会出现 死锁，
     */

    /**
     * 死锁的必要条件
     * 互斥条件
     * 在一段时间内某资源仅为一个线程所占有。此时若有其他线程请求该资源，则请求线程只能等待。
     * 不可剥夺条件
     * 线程所获得的资源在未使用完毕之前，不能被其他线程强行夺走，即只能由获得该资源的线程自己来释放（只能是主动释放)。
     * 请求与保持条件
     * 线程已经保持了至少一个资源，但又提出了新的资源请求，而该资源已被其他线程占有，此时请求线程被阻塞，但对自己已获得
     * 的资源保持不放。
     * 循环等待条件
     * <p>
     * 死锁的预防
     * 并发编程中，一旦发生了死锁的现象，则基本没有特别好的解决方法，一般情况下只能重启应用来解决。因此，解决死锁的最好
     * 方法就是预防死锁。
     * 破坏互斥条件
     * 互斥条件是我们没办法破坏的，因为我们使用锁为的就是线程之间的互斥。这一点需要特别注意！！！！
     * 破坏不可剥夺条件
     * 破坏不可剥夺的条件的核心就是让当前线程自己主动释放占有的资源，关于这一点，synchronized是做不到的，我们可以使用
     * java.util.concurrent包下的Lock来解决。
     */

    private Lock thisLock = new ReentrantLock();
    private Lock targetLock = new ReentrantLock();

    public void transfer3(TransferAccount target, Integer transferMoney) {
        // tryLock()方法是有返回值的，它表示用来尝试获取锁，如果获取成功，则返回true，如果获取失败（即锁已被其他线程获取），
        // 则返回false，也就说这个方法无论如何都会立即返回。在拿不到锁时不会一直在那等待。
        boolean isThisLock = thisLock.tryLock();
        if (isThisLock) {
            try {
                boolean isTargetLock = targetLock.tryLock();
                if (isTargetLock) {
                    try {
                        if (this.balance >= transferMoney) {
                            this.balance -= transferMoney;
                            target.balance += transferMoney;
                        }
                    } finally {
                        targetLock.unlock();
                    }
                }
            } finally {
                thisLock.unlock();
            }
        }
    }

    /**
     * 破坏请求与保持条件
     * 破坏请求与保持条件，我们可以一次性申请所需要的所有资源，例如在我们完成转账操作的过程中，我们一次性申请账户A和账
     * 户B，两个账户都申请成功后，再执行转账的操作。此时，我们需要再创建一个申请资源的类ResourcesRequester，这个类的作
     * 用就是申请资源和释放资源。同时，TansferAccount类中需要持有一个ResourcesRequester类的单例对象，当我们需要执行转
     * 账操作时，首先向ResourcesRequester同时申请转出账户和转入账户两个资源，申请成功后，再锁定两个资源；当转账操作完
     * 成后，释放锁并释放ResourcesRequester类申请的转出账户和转入账户资源。
     */
    public class ResourceRequester {
        // 存放申请资源的集合
        private List<Object> resources = new ArrayList<>();

        // 一次申请所有的资源
        public synchronized boolean applyResources(Object source, Object target) {
            if (resources.contains(source) || resources.contains(target)) {
                return false;
            }
            resources.add(source);
            resources.add(target);
            return true;
        }

        // 释放资源
        public synchronized void releaseResource(Object source, Object target) {
            resources.remove(source);
            resources.remove(target);
        }
    }

    // 此时，TansferAccount类的代码如下所示。
    private ResourceRequester resourceRequester;

    public void transfer4(TransferAccount target, Integer transferMoney) {
        // 自旋申请转入转出账户，知道成功
        while (!resourceRequester.applyResources(this, target)) {
            // 循环体为空
            ;
        }
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

    }

    /**
     * 破坏循环等待条件
     * 破坏循环等待条件，则可以通过对资源排序，按照一定的顺序来申请资源，然后按照顺序来锁定资源，可以有效的避免死锁。
     * 例如，在我们的转账操作中，往往每个账户都会有一个唯一的id值，我们在锁定账户资源时，可以按照id值从小到大的顺序来申
     * 请账户资源，并按照id从小到大的顺序来锁定账户，此时，程序就不会再进行循环等待了。
     * 程序代码如下所示。
     */
    // 账户id
    private Integer id;

    public void transfer5(TransferAccount target, Integer transferMoney) {
        TransferAccount beforeAccount = this;
        TransferAccount afterAccount = target;
        if (this.id > target.id) {
            beforeAccount = target;
            afterAccount = this;
        }
        synchronized (beforeAccount) {
            synchronized (afterAccount) {
                if (this.balance >= transferMoney) {
                    this.balance -= transferMoney;
                    target.balance += transferMoney;
                }
            }
        }

    }
    /**
     * 在并发编程中，使用细粒度锁来锁定多个资源时，要时刻注意死锁的问题。另外，避免死锁最简单的方法就是阻止循环等待条
     * 件，将系统中所有的资源设置标志位、排序，规定所有的线程申请资源必须以一定的顺序来操作进而避免死锁。
     */
}

