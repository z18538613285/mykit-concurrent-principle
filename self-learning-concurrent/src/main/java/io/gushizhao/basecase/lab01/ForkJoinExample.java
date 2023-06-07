package io.gushizhao.basecase.lab01;

import java.util.Arrays;
import java.util.List;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 10:32
 *
 * ForkJoin框架的本质是一个用于并行执行任务的框架， 能够把一个大任务分割成若干个小任务，最终汇总每个小任务结果后得到
 * 大任务的计算结果。在Java中，ForkJoin框架与ThreadPool共存，并不是要替换ThreadPool
 * 其实，在Java 8中引入的并行流计算，内部就是采用的ForkJoinPool来实现的。
 *
 * 它使用了一个无限队列来保存需要执行的任务，而线程的数量则是通过构造函数传入，如果没有向构造函数中传入指定的线程数量，那么
 * 当前计算机可用的CPU数量会被设置为线程数量作为默认值
 *
 * ForkJoinPool主要使用分治法(Divide-and-Conquer Algorithm)来解决问题。典型的应用比如快速排序算法。这里的要点在
 * 于，ForkJoinPool能够使用相对较少的线程来处理大量的任务。比如要对1000万个数据进行排序，那么会将这个任务分割成两个
 * 500万的排序任务和一个针对这两组500万数据的合并任务。以此类推，对于500万的数据也会做出同样的分割处理，到最后会设
 * 置一个阈值来规定当数据规模到多少时，停止这样的分割处理。比如，当元素的数量小于10时，会停止分割，转而使用插入排序
 * 对它们进行排序。那么到最后，所有的任务加起来会有大概200万+个。问题的关键在于，对于一个任务而言，只有当它所有的子
 * 任务完成之后，它才能够被执行。
 *
 * 所以当使用ThreadPoolExecutor时，使用分治法会存在问题，因为ThreadPoolExecutor中的线程无法向任务队列中再添加一个
 * 任务并在等待该任务完成之后再继续执行。而使用ForkJoinPool就能够解决这个问题，它就能够让其中的线程创建新的任务，并
 * 挂起当前的任务，此时线程就能够从队列中选择子任务执行。
 *
 * 使用ThreadPoolExecutor或者ForkJoinPool，性能上会有什么差异呢？
 * 首先，使用ForkJoinPool能够使用数量有限的线程来完成非常多的具有父子关系的任务，比如使用4个线程来完成超过200万个任
 * 务。但是，使用ThreadPoolExecutor时，是不可能完成的，因为ThreadPoolExecutor中的Thread无法选择优先执行子任务，需
 * 要完成200万个具有父子关系的任务时，也需要200万个线程，很显然这是不可行的，也是很不合理的！！
 *
 *
 * 工作窃取算法
 * 假如我们需要做一个比较大的任务，我们可以把这个任务分割为若干互不依赖的子任务，为了减少线程间的竞争，于是把这些子
 * 任务分别放到不同的队列里，并为每个队列创建一个单独的线程来执行队列里的任务，线程和队列一一对应，比如A线程负责处
 * 理A队列里的任务。但是有的线程会先把自己队列里的任务干完，而其他线程对应的队列里还有任务等待处理。干完活的线程与
 * 其等着，不如去帮其他线程干活，于是它就去其他线程的队列里窃取一个任务来执行。而在这时它们会访问同一个队列，所以为
 * 了减少窃取任务线程和被窃取任务线程之间的竞争，通常会使用双端队列，被窃取任务线程永远从双端队列的头部拿任务执行，
 * 而窃取任务的线程永远从双端队列的尾部拿任务执行。
 *
 * 工作窃取算法的优点：
 * 充分利用线程进行并行计算，并减少了线程间的竞争。
 *
 * 工作窃取算法的缺点：
 * 在某些情况下还是存在竞争，比如双端队列里只有一个任务时。并且该算法会消耗更多的系统资源，比如创建多个线程和多个双
 * 端队列。
 *
 * Fork/Join框架局限性：
 * 对于Fork/Join框架而言，当一个任务正在等待它使用Join操作创建的子任务结束时，执行这个任务的工作线程查找其他未被执行
 * 的任务，并开始执行这些未被执行的任务，通过这种方式，线程充分利用它们的运行时间来提高应用程序的性能。为了实现这个
 * 目标，Fork/Join框架执行的任务有一些局限性。
 * （1）任务只能使用Fork和Join操作来进行同步机制，如果使用了其他同步机制，则在同步操作时，工作线程就不能执行其他任务
 * 了。比如，在Fork/Join框架中，使任务进行了睡眠，那么，在睡眠期间内，正在执行这个任务的工作线程将不会执行其他任务了。
 * （2）在Fork/Join框架中，所拆分的任务不应该去执行IO操作，比如：读写数据文件。
 * （3）任务不能抛出检查异常，必须通过必要的代码来出来这些异常。
 *
 * 可以使用Executors.newWorkStealPool()方法创建ForkJoinPool。
 * ForkJoinPool中提供了如下提交任务的方法。
 * public void execute(ForkJoinTask<?> task)
 * public void execute(Runnable task)
 * public <T> T invoke(ForkJoinTask<T> task)
 * public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
 * public <T> ForkJoinTask<T> submit(ForkJoinTask<T> task)
 * public <T> ForkJoinTask<T> submit(Callable<T> task)
 * public <T> ForkJoinTask<T> submit(Runnable task, T result)
 * public ForkJoinTask<?> submit(Runnable task)
 *
 * 2.ForkJoinWorkerThread类
 * 实现ForkJoin框架中的线程。
 * 3.ForkJoinTask类
 * ForkJoinTask封装了数据及其相应的计算，并且支持细粒度的数据并行。ForkJoinTask比线程要轻量，ForkJoinPool中少量工作
 * 线程能够运行大量的ForkJoinTask。
 *
 * ForkJoinTask类中主要包括两个方法fork()和join()，分别实现任务的分拆与合并。
 * fork()方法类似于Thread.start()，但是它并不立即执行任务，而是将任务放入工作队列中。跟Thread.join()方法不同，
 * ForkJoinTask的join()方法并不简单的阻塞线程，而是利用工作线程运行其他任务，当一个工作线程中调用join()，它将处理其他任
 * 务，直到注意到目标子任务已经完成。
 *
 * ForkJoinTask有3个子类：
 * RecursiveAction：无返回值的任务。 无返回结果的ForkJoinTask实现Runnable。
 * RecursiveTask：有返回值的任务。 有返回结果的ForkJoinTask实现Callable。
 * CountedCompleter：完成任务后将触发其他任务。 在任务完成执行后会触发执行一个自定义的钩子函数。
 *
 *
 */
public class ForkJoinExample {


    // 使用并行流实现打印数组元组的程序。
    public static void main(String[] args) {
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9);
        numberList.parallelStream().forEach(System.out::println);
    }
}
