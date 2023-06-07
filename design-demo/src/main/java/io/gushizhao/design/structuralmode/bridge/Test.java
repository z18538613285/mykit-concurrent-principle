package io.gushizhao.design.structuralmode.bridge;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 14:17
 */
public class Test {
    public static void main(String[] args) {
        Person man = new Man();
        Person lady = new Lady();
        Clothing jacket = new Jacket();
        Clothing trouser = new Trouser();
        jacket.personDressCloth(man);
        trouser.personDressCloth(man);

        jacket.personDressCloth(lady);
        trouser.personDressCloth(lady);
    }
    /**
     * 男人穿马甲
     * 男人穿裤子
     * 女人穿马甲
     * 女人穿裤子
     */
}
