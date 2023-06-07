package io.gushizhao.design.structuralmode.bridge;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 11:31
 *
 * RefinedAbstraction
 *
 */
public class Man extends Person{

    public Man() {
        setType("男人");
    }

    @Override
    public void dress() {
        Clothing clothing = getClothing();
        clothing.personDressCloth(this);
    }
}
