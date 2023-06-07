package io.gushizhao.design.structuralmode.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 14:53
 *
 * FlyWeightFactory
 */
public class FlyWeightFactory {

    private static Map flyWeights = new HashMap();

    public FlyWeightFactory(String arg) {
        flyWeights.put(arg, new FlyWeightImpl());
    }

    public static FlyWeight getFlyWeight(String key) {
        if (flyWeights.get(key) == null) {
            flyWeights.put(key, new FlyWeightImpl());
        }
        return (FlyWeight) flyWeights.get(key);
    }

    public static int getSize() {
        return flyWeights.size();
    }
}
