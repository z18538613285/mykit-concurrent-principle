package io.gushizhao.jdk.lab04;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/22 16:01
 *
 * 在Java的并发容器中，Queue相对来说比较复杂。我们先来了解几个概念：
 * 阻塞队列：阻塞一般就是指当队列已满时，入队操作会阻塞；当队列为空时，出队操作就会阻塞。
 * 非阻塞队列：队列的入队和出队操作不会阻塞。
 * 单端队列：队列的入队操作只能在队尾进行，队列的出队操作只能在队首进行。
 * 双端队列：队列的入队操作和出队操作都可以在队首和队尾进行。
 * 我们可以将上述的队列进行组合，将队列分为单端阻塞队列、双端阻塞队列、单端非阻塞队列和双端非阻塞队列。
 *
 * 在Java的并发容器中，会使用明显的标识来区分不同类型的队列。
 * 阻塞队列一个明显的标识就是使用Blocking修饰，例如，ArrayBlockingQueue和LinkedBlockingQueue都是阻塞队列。
 * 单端队列会使用Queue标识，例如ArrayBlockingQueue和LinkedBlockingQueue也是单端队列。
 * 双端队列会使用Deque标识，例如LinkedBlockingDeque和ConcurrentLinkedDeque都是双端队列。
 *
 * 单端阻塞队列
 * 在Java的并发容器中，单端阻塞队列的主要实现是BlockingQueue，主要包括：ArrayBlockingQueue、
 * LinkedBlockingQueue、SynchronousQueue、LinkedTransferQueue、PriorityBlockingQueue和DelayQueue。
 *
 * 单端阻塞队列的内部一般会有一个队列。
 * 在实现上，内部的队列可以是数组，例如ArrayBlockingQueue，也可以是链表，例如LinkedBlockingQueue。
 * 也可以在内部不存在队列，例如SynchronousQueue，SynchronousQueue实现了生产者的入队操作必须等待消费者的出队操作
 * 完成之后才能进行。
 * LinkedTransferQueue集成了LinkedBlockingQueue和SynchronousQueue的优点，并且性能比LinkedBlockingQueue好。
 * PriorityBlockingQueue实现了按照优先级进行出队操作，也就是说，队列元素在PriorityBlockingQueue内部可以按照某种规则
 * 进行排序。
 * DelayQueue是延时队列，实现了在一段时间后再出队的操作。
 *
 *
 * 双端阻塞队列
 * 双端阻塞队列的实现主要是LinkedBlockingDeque。
 *
 * 单端非阻塞队列
 * 单端非阻塞队列的实现主要是ConcurrentLinkedQueue，
 *
 * 双端非阻塞队列
 * 双端非阻塞队列的实现主要是ConcurrentLinkedDeque，
 *
 *有界与无界队列
 * 使用队列时，还要注意队列的有界与无界问题，也就是在使用队列时，需要注意队列是否有容量限制。
 * 在实际工作中，一般推荐使用有界队列。因为无界队列很容易导致内存溢出的问题。在Java的并发容器中，只有
 * ArrayBlockingQueue和LinkedBlockingQueue支持有界，其他的队列都是无界队列。
 * 在使用时，一定要注意内存溢出问题。
 *
 */
public class TestQueue {
}
