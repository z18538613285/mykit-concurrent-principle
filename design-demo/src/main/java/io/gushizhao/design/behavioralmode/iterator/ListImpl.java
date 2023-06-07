package io.gushizhao.design.behavioralmode.iterator;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 9:29
 *
 * ConcreteAggregate
 *
 */
public class ListImpl implements List{
    private Object[] list;

    private int index;

    private int size;

    public ListImpl() {
        index = 0;
        size = 0;
        list = new Object[100];
    }

    @Override
    public Iterator iterator() {
        return new IteratorImpl(this);
    }

    @Override
    public Object get(int index) {
        return list[index];
    }

    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public void add(Object obj) {
        list[index ++] = obj;
        size ++;
    }
}
