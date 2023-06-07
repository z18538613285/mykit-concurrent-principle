package io.gushizhao.design.structuralmode.composite;

import java.util.List;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 14:42
 */
public class Test {
    public static void main(String[] args) {
        Employer pm = new ProjectManager("项目经理");
        Employer pa = new ProjectAssistant("项目助理");
        Employer programmer1 = new Programmer("程序员一");
        Employer programmer2 = new Programmer("程序员二");

        pm.add(pa); // 为项目经理添加项目助理
        pm.add(programmer2); // 为项目经理添加程序员

        List<Employer> employers = pm.getEmployers();
        for (Employer employer : employers) {
            System.out.println(employer.getName());
        }

    }
    /**
     * 项目助理
     * 程序员二
     */
}
