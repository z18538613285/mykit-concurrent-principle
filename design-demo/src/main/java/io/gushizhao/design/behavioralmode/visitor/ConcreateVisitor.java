package io.gushizhao.design.behavioralmode.visitor;

import java.util.Collection;
import java.util.Iterator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:29
 *
 * ConcreateVisitor
 */
public class ConcreateVisitor implements Visitor{
    @Override
    public void visitString(StringElement stringElement) {
        System.out.println(stringElement.getString());
    }

    @Override
    public void visitFloat(FloatElement floatElement) {
        System.out.println(floatElement.getaFloat());
    }

    @Override
    public void visitCollection(Collection collection) {
        // Auto-generated method stub
        Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            Object next = iterator.next();
            if (next instanceof Visitable) {
                ((Visitable) next).accept(this);
            }
        }
    }
}
