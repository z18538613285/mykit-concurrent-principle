package io.gushizhao.design.behavioralmode.template;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:10
 *
 * AbstractClass
 */
public abstract class Template {
    public abstract void print();

    public void update() {
        System.out.println("开始打印");
        for (int i = 0; i < 10; i++) {
            print();
        }
    }
}
