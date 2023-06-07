package io.gushizhao.test;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/29 17:52
 */
public class Test {
    public static void main(String[] args) {
        String cityId = "330310000000";

        //String preCityId = cityId.replaceAll("0*$", "");
        // 去除偶数个零
        String preCityId = cityId.replaceAll("(?:00)*$", "");
        System.out.println(preCityId);



    }
}
