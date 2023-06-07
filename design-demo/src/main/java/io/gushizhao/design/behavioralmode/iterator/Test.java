package io.gushizhao.design.behavioralmode.iterator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 9:36
 */
public class Test {
    public static void main(String[] args) {
        List list = new ListImpl();
        list.add("a");
        list.add("b");
        list.add("c");

        // 第一种迭代方式
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println("=============");

        // 第二种迭代方式
        for (int i = 0; i < list.getSize(); i++) {
            System.out.println(list.get(i));
        }
    }
    /**
     * a
     * b
     * c
     * =============
     * a
     * b
     * c
     */
}
