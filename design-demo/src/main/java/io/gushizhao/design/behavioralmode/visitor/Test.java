package io.gushizhao.design.behavioralmode.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:34
 */
public class Test {
    public static void main(String[] args) {
        Visitor visitor = new ConcreateVisitor();
        StringElement stringElement = new StringElement("abc");
        stringElement.accept(visitor);

        FloatElement fe = new FloatElement(new Float(1.5));
        fe.accept(visitor);
        System.out.println("===========");
        List result = new ArrayList();
        result.add(new StringElement("abc"));
        result.add(new StringElement("abc"));
        result.add(new StringElement("abc"));
        result.add(new FloatElement(new Float(1.5)));
        result.add(new FloatElement(new Float(1.5)));
        result.add(new FloatElement(new Float(1.5)));
        visitor.visitCollection(result);
    }
    /**
     * abc
     * 1.5
     * ===========
     * abc
     * abc
     * abc
     * 1.5
     * 1.5
     * 1.5
     */
}
