package io.gushizhao.design.behavioralmode.visitor;

/**
 * Element
 */
public interface Visitable {
    public void accept(Visitor visitor);
}
