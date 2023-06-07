package io.gushizhao.jdk.lab01;

import cn.hutool.core.collection.CollectionUtil;
import com.sun.org.apache.xerces.internal.impl.xs.identity.Field;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 14:08
 */
public class ReadWriteLockCache<K, V> {

    private final Map<K, V> m = new HashMap<>();
    private final ReadWriteLock rwl = new ReentrantReadWriteLock();
    //读锁
    private final Lock r = rwl.readLock();
    //写锁
    private final Lock w = rwl.writeLock();

    // 读缓存
    // 在get()方法中使用了读锁，get()方法可以被多个线程同时执行读操作；
    public V get(K key) {
        r.lock();
        try {
            return m.get(key);
        } finally {
            r.unlock();
        }
    }

    /**
     * 这里需要注意的是：无论是读锁还是写锁，锁的释放操作都需要放到 finally{} 代码块中。
     *
     * 在以往的经验中，有两种向缓存中加载数据的方式，一种是：项目启动时，将数据全量加载到缓存中，一种是在项目运行期间，
     * 按需加载所需要的缓存数据。
     */
    // 写缓存
    // put()方法
    //内部使用写锁，也就是说，put()方法在同一时刻只能有一个线程对缓存进行写操作。
    public V put(K key, V value) {
        w.lock();
        try {
            return m.put(key, value);
        } finally {
            w.unlock();
        }
    }

    // 全量加载缓存
    public ReadWriteLockCache() {
        // 查询数据库
        List<Map<K, V>> list = new ArrayList<>();
        if (!CollectionUtil.isEmpty(list)) {
            list.parallelStream().forEach(f -> {
                m.put((K) f.get(null), f.get(null));
            });
        }
    }

    // 按需加载缓存
    V getTwo(K key) {
        V v = null;
        // 读缓存
        r.lock();
        try {
            v = m.get(key);
        } finally {
            r.unlock();
        }
        // 缓存中存在，返回
        if (v != null) {
            return v;
        }
        // 缓存中不存在。查询数据库
        w.lock();
        try {
            // 再次验证缓存中是否存在数据
            /**
             * 为啥程序都已经添加写锁了，在写锁内部为啥还要查询一次缓存呢？
             *
             * 这是因为在高并发的场景下，可能会存在多个线程来竞争写锁的现象。例如：第一次执行get()方法时，缓存中的数据为空。如果
             * 此时有三个线程同时调用get()方法，同时运行到 w.lock() 代码处，由于写锁的排他性。此时只有一个线程会获取到写锁，其他
             * 两个线程则阻塞在 w.lock() 处。获取到写锁的线程继续往下执行查询数据库，将数据写入缓存，之后释放写锁。
             * 此时，另外两个线程竞争写锁，某个线程会获取到锁，继续往下执行，如果在 w.lock() 后没有 v = m.get(key); 再次查询缓存
             * 的数据，则这个线程会直接查询数据库，将数据写入缓存后释放写锁。最后一个线程同样会按照这个流程执行。
             *
             * ，在 w.lock() 后添加 v = m.get(key); 再次查询缓存的数据，能够有效的减少高并发场景下重复
             * 查询数据库的问题，提升系统的性能。
             */
            v = m.get(key);
            if (v == null) {
                // 查询数据库
                v = (V) "从数据库查询数据";
                m.put(key, v);
            }
        } finally {
            w.unlock();
        }
        return v;

    }
}
