package io.gushizhao.design.structuralmode.bridge;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 11:31
 *
 * RefinedAbstraction
 *
 */
public class Lady extends Person{

    public Lady() {
        setType("女人");
    }

    @Override
    public void dress() {
        Clothing clothing = getClothing();
        clothing.personDressCloth(this);
    }
}
