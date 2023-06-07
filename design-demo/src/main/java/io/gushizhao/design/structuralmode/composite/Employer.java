package io.gushizhao.design.structuralmode.composite;

import java.util.List;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 14:27
 *
 * Component
 * 定义Component 类Employer
 */
public abstract class Employer {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void add(Employer employer);

    public abstract void delete(Employer employer);

    public List<Employer> employers;

    public void printInfo() {
        System.out.println(name);
    }

    public List<Employer> getEmployers() {
        return this.employers;
    }

}
