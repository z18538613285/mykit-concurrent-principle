package io.gushizhao.concurrent.lab03;

/**
 * @Author huzhichao
 * @Description  线程的顺序，Thread.join()方法能够确保线程的执行顺序
 * @Date 2023/3/20 9:59
 */
public class ThreadSort02 {

    public static void main(String[] args) throws InterruptedException{
        Thread thread1 = new Thread(() -> {
            System.out.println("thread1");
        });
        Thread thread2 = new Thread(() -> {
            System.out.println("thread2");
        });
        Thread thread3 = new Thread(() -> {
            System.out.println("thread3");
        });

        thread1.start();

        // 实际上让主线程等待子线程完成
        /**
         * 调用线程的wait()方法时，会使主线程处于等待状态，等待子线程执行完成后再次向下执行。也就是说，在
         * ThreadSort02类的main()方法中，调用子线程的join()方法，会阻塞main()方法的执行，当子线程执行完成后，main()方法会
         * 继续向下执行，启动第二个子线程，并执行子线程的业务逻辑，以此类推。
         */
        thread1.join();

        thread2.start();
        thread2.join();

        thread3.start();
        thread3.join();
    }
}
