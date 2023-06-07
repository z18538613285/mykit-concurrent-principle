package io.gushizhao.design.structuralmode.bridge;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 11:29
 *
 * Abstraction
 * 定义Abstraction Person类
 */
public abstract class Person {
    private Clothing clothing;
    private String type;

    public Clothing getClothing() {
        return clothing;
    }

    public void setClothing(Clothing clothing) {
        this.clothing = clothing;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public abstract void dress();
}
