package io.gushizhao.design.creationmode.factorymethod;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 9:16
 */
public class Test {

    public static void main(String[] args) {
        IWorkFactory studentWorkFactory = new StudentWorkFactory();
        studentWorkFactory.getWork().doWork();

        IWorkFactory teacherWorkFactory = new TeacherWorkFactory();
        teacherWorkFactory.getWork().doWork();
    }
    /**
     * 学生做作业！
     * 老师审批作业！
     */
}

