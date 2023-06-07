package io.gushizhao.basecase.lab05;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/24 9:21
 *
 * 并发问题的“幕后黑手”
 * 源头一，缓存导致的可见性问题
 * 可见性就是说一个线程对共享变量的修改，另一个线程能够立刻看到。
 * 由于只有一个CPU内核，线程A和线程B无论谁修改了CPU中的变量V，另一个线程读到的变量V一定是最新的值。
 *
 *
 */
public class TestThread01 {

    // 测试线程的可见性
    private long count = 0;
    // 对count的值累加 1000 次
    private void addCount() {
        for (int i = 0; i < 100000; i++) {
            count ++;
        }
    }

    public long execute() throws InterruptedException {
        // 创建两个线程，执行 count 的累加操作
        Thread threadA = new Thread(() -> {
            addCount();
        });
        Thread threadB = new Thread(() -> {
            addCount();
        });
        threadA.start();
        threadB.start();
        threadA.join();
        threadB.join();

        return count;
    }

    public static void main(String[] args) throws InterruptedException{
        TestThread01 testThread01 = new TestThread01();
        long count = testThread01.execute();
        System.out.println(count);
    }
}
