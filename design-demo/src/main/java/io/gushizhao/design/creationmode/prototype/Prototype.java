package io.gushizhao.design.creationmode.prototype;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 9:40
 *
 * Prototype
 *
 * 原型类，实现Cloneable接口
 */
public class Prototype implements Cloneable{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone(){
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }
}
