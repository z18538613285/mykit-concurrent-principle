package io.gushizhao.design.behavioralmode.visitor;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 11:26
 */
public class StringElement implements Visitable{
    private String string;

    public StringElement(String string) {
        this.string = string;
    }

    public String getString() {
        return string;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visitString(this);
    }
}
