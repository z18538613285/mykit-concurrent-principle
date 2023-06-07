package io.gushizhao.basecase.lab03;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;

import java.util.Arrays;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/23 14:17
 * <p>
 * 如何安全的发布对象
 * 发布对象：使一个对象能够被当前范围之外的代码所使用
 * 对象溢出：是一种错误的发布，当一个对象还没有构造完成时，就使它被其他线程所见
 *
 * 注意：在对象未构造完成之前，不可以将其发布
 * 如何安全的发布对象：
 * （1）在静态初始化函数中初始化一个对象引用
 * （2）将对象的引用保存到volatile类型域或者AtomicReference对象中
 * （3）将对象的引用保存到某个正确构造对象的final类型域中
 * （4）将对象的引用保存到一个由锁保护的域中
 */
public class UnsafePublish {

    private static Log log = LogFactory.get(UnsafePublish.class);

    private String[] states = {"a", "b", "c"};

    public String[] getStates() {
        return states;
    }

    /**
     * 不安全的发布示例代码
     *
     * 其中，每个线程都能获取到UnsafePublish类的私有成员变量states，并修改states数组的元素值，造成其他线程获取的states元
     * 素值不确定。
     * @param args
     */
    public static void main(String[] args) {
        UnsafePublish unsafePublish = new UnsafePublish();
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
        unsafePublish.getStates()[0] = "d";
        log.info("{}", Arrays.toString(unsafePublish.getStates()));
    }
}
