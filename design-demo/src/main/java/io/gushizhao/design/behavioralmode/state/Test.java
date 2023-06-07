package io.gushizhao.design.behavioralmode.state;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:59
 */
public class Test {
    public static void main(String[] args) {
        Context context1 = new Context();
        context1.setWeather(new Sunshine());
        System.out.println(context1.weatherMessage());

        System.out.println("===============");

        Context context2 = new Context();
        context2.setWeather(new Rain());
        System.out.println(context2.weatherMessage());
    }
    /**
     * 阳光
     * ===============
     * 下雨
     */
}
