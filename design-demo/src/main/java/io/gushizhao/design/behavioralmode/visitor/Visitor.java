package io.gushizhao.design.behavioralmode.visitor;

import java.util.Collection;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:27
 *
 * Visitor
 */
public interface Visitor {
    public void visitString(StringElement stringElement);
    public void visitFloat(FloatElement floatElement);

    public void visitCollection(Collection collection);
}
