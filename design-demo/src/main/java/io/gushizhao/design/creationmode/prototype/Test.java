package io.gushizhao.design.creationmode.prototype;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 9:44
 */
public class Test {
    public static void main(String[] args) {
        Prototype pro = new ConcreatePrototype("prototype");
        Prototype clone = (Prototype) pro.clone();
        System.out.println(pro.getName());
        System.out.println(clone.getName());
    }

    /**
     * prototype
     * prototype
     */
}
