package io.gushizhao.design.behavioralmode.interpreter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 9:17
 */
public class Context {
    private String content;
    private List<Expression> list = new ArrayList<>();

    public String getContent() {
        return content;
    }

    public List<Expression> getList() {
        return list;
    }

    public void add(Expression eps) {
        list.add(eps);
    }

}
