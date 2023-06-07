package io.gushizhao.design.structuralmode.composite;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 14:37
 *
 * 定义Leaf类ProjectAssistant
 */
public class ProjectAssistant extends Employer{
    public ProjectAssistant(String name) {
        setName(name);
        employers = null; // 项目助理，表示没有下属了
    }

    @Override
    public void add(Employer employer) {

    }

    @Override
    public void delete(Employer employer) {

    }
}
