package io.gushizhao.design.behavioralmode.state;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:52
 *
 * ConcreateStatesubclasses
 */
public class Rain implements Weather{
    @Override
    public String getWeather() {
        return "下雨";
    }
}
