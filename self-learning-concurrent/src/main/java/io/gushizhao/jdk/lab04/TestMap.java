package io.gushizhao.jdk.lab04;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 15:56
 *
 * Map接口的实现类主要有ConcurrentHashMap和ConcurrentSkipListMap，而ConcurrentHashMap和
 * ConcurrentSkipListMap最大的区别就是：ConcurrentHashMap的Key是无序的，而ConcurrentSkipListMap的Key是有序
 * 的。
 *
 * Map的实现类 Key是否可为空 Value是否可为空 是否是线程安全的
 *                          key是否可为空  Value是否可为空   是否线程安全
 * HashMap                      是           是               否
 * TreeMap                      否           是               否
 * HashTable                    否           否               是
 * ConcurrentHashMap            否           否               是
 * ConcurrentSkipListMap        否           否               是
 *
 * 这里，ConcurrentSkipListMap是基于“跳表”实现的，跳表的插入、删除、查询的平均时间复杂度为O(log n)，这些时间复杂度在
 * 理论上与线程数没有关系。如果要追求性能的话，可以尝试使用ConcurrentSkipListMap。
 */
public class TestMap {
}
