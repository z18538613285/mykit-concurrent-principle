package io.gushizhao.design.structuralmode.composite;

import java.util.ArrayList;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 14:39
 *
 * 定义Composite类ProjectManager类
 */
public class ProjectManager extends Employer{
    public ProjectManager(String name) {
        setName(name);
        employers = new ArrayList<Employer>();
    }

    @Override
    public void add(Employer employer) {
        employers.add(employer);
    }

    @Override
    public void delete(Employer employer) {
        employers.remove(employer);
    }
}
