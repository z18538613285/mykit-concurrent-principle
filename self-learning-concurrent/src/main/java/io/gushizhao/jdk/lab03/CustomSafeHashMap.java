package io.gushizhao.jdk.lab03;

import java.util.*;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 15:36
 *
 * 在JDK中，总体上可以将容器分为同步容器和并发容器。
 * 同步容器一般指的是JDK1.5版本之前的线程安全的容器，同步容器有个最大的问题，就是性能差，容器中的所有方法都是用
 * synchronized保证互斥，串行度太高。在JDK1.5之后提供了性能更高的线程安全的容器，我们称之为并发容器。
 *
 * 无论是同步容器还是并发容器，都可以将其分为四个大类，分别为：List、Set、Map和Queue，
 *
 * 如何将一个不是线程安全的容器变成线程安全的呢？ 相信有很
 * 多小伙伴都能够想到一个办法，那就是把非线程安全的容器的方法都加上synchronized锁，使这些方法的访问都变成同步的。
 *
 * 是不是所有的非线程安全的容器类都可以通过为方法添加synchronized锁来保证方法的原子
 * 性，从而使容器变得安全呢？
 * 是的，我们可以通过为非线程安全的容器方法添加synchronized锁来解决容器的线程安全问题。其实，在JDK中也是这么做的。
 * 例如，在JDK中提供了线程安全的List、Set和Map，它们都是通过synchronized锁保证线程安全的。
 *
 * 同步容器有哪些坑呢？
 * 1、竞态条件问题
 * 2、使用迭代器遍历容器
 *
 *
 * 在Java中，同步容器一般都是基于synchronized锁实现的，有些是通过包装类实现的，例如List、Set、Map等。有些不是通过包
 * 装类实现的，例如Vector、Stack、HashTable等。
 * 对于这些容器的遍历操作，一定要为容器添加互斥锁保证整体的原子性。
 */
public class CustomSafeHashMap<K, V> {
    private Map<K, V> innerMap = new HashMap<K, V>();
    public synchronized void put(K k, V v){
        innerMap.put(k, v);
    }
    public synchronized V get(K k){
        return innerMap.get(k);
    }
    // 在并发编程中，组合操作要时刻注意竞态条件，
    // 组合操作。在高并发环境中，存在组合操作的方法可能就会存在竞态条件。
    //也就是说，在并发编程中，即使每个操作都能保证原子性，也不能保证组合操作的原子性。
    public synchronized void putIfNotExists(K k, V v){
        if(!innerMap.containsKey(k)){
            innerMap.put(k, v);
        }
    }

    // 我们可以通过如下方式创建线程安全的List、Set和Map。
    List list = Collections.synchronizedList(new ArrayList());
    Set set = Collections.synchronizedSet(new HashSet());
    Map map = Collections.synchronizedMap(new HashMap());


    // 一个容易被人忽略的坑就是使用迭代器遍历容器，对容器中的每个元素调用一个方法，这就存在了并发问题，这些组合操作不具
    //备原子性。
    void testIterator() {
        List list = Collections.synchronizedList(new ArrayList());
        Iterator iterator = list.iterator();
        while (iterator.hasNext()){
            //format(iterator.next());
        }

        // 如何解决这个问题呢？一个很简单的方式就是锁住list集合，
        // 这里，为何锁住list集合就能够解决并发问题呢？
        //这是因为在Collections类中，其内部的包装类的公共方法锁住的对象是this，其实就是上面代码中的list，所以，我们对list加锁
        //后，就能够保证线程的安全性了。
        List synchronizedList = Collections.synchronizedList(new ArrayList());
        synchronized(synchronizedList){
            Iterator iterator2 = synchronizedList.iterator();
            while (iterator2.hasNext()){
                //format(iterator2.next());
            }
        }
    }

}
