package io.gushizhao.design.creationmode.builder;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 9:35
 */
public class Test {
    public static void main(String[] args) {
        PersonDirector pd = new PersonDirector();
        Person person = pd.constructPerson(new ManBuilder());
        System.out.println(person.getBody());
        System.out.println(person.getHead());
        System.out.println(person.getFoot());
    }
    /**
     * 建造男人的身体
     * 建造那人的头
     * 建造男人的脚
     */
}
