package io.gushizhao.concurrent.lab04;

/**
 * 定义回调接口
 * @param <T>
 */
public interface TaskCallable<T> {

    // 便于接口的通用型，这里为回调接口定义了泛型。
    T callable(T t);
}
