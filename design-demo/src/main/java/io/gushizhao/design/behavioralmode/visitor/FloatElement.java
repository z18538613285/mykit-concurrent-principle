package io.gushizhao.design.behavioralmode.visitor;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:20
 *
 * ConcreateElement
 */
public class FloatElement implements Visitable{

    private Float aFloat;

    public FloatElement(Float aFloat) {
        this.aFloat = aFloat;
    }

    public Float getaFloat() {
        return aFloat;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitFloat(this);
    }
}
