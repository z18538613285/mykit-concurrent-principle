package io.gushizhao.jdk.lab07;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 17:47
 *
 * AQS中的ReentrantLock、ReentrantReadWriteLock、StampedLock与Condition
 *
 * ReentrantReadWriteLock
 *
 * 在没有任何读写锁的时候，才可以取得写锁。如果一直有读锁存在，则无法执行写锁，这就会导致写锁饥饿。
 *
 * 我们可以这样选择使用synchronozed锁还是ReentrantLock锁
 * 1、当只有少量竞争者时，synchronized是一个很好的通用锁实现
 * 2、竞争者不少，但是线程的增长趋势是可预估的，此时，ReentrantLock是一个很好的通用锁实现
 * 3、synchronized不会引发死锁，其他的锁使用不当可能会引发死锁。
 *
 */
public class ReentrantReadWriteLockExample {

    private static Log log = LogFactory.get(ReentrantReadWriteLockExample.class);
    private final Map<String, Data> map = new TreeMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    public Data get(String key){
        readLock.lock();
        try{
            return map.get(key);
        }finally {
            readLock.unlock();
        }
    }
    public Set<String> getAllKeys(){
        readLock.lock();
        try{
            return map.keySet();
        }finally {
            readLock.unlock();
        }
    }
    public Data put(String key, Data value){
        writeLock.lock();
        try{
            return map.put(key, value);
        }finally {
            writeLock.unlock();
        }
    }
    class Data{
    }
}
