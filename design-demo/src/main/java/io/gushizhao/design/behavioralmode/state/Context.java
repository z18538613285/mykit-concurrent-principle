package io.gushizhao.design.behavioralmode.state;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:50
 *
 * Context
 */
public class Context {

    private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public String weatherMessage() {
        return weather.getWeather();
    }
}
