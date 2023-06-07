package io.gushizhao.design.behavioralmode.observer;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/3 10:37
 */
public class Test {
    public static void main(String[] args) {
        Policeman thPol = new TianHePoliceman();
        Policeman hpPol = new HunagPuPoliceman();

        Citizen citizen = new HuangPuCitizen(hpPol);
        citizen.sendMessage("unnormal");
        citizen.sendMessage("normal");

        System.out.println("=============");

        citizen = new TianHeCitizen(thPol);
        citizen.sendMessage("unnormal");
        citizen.sendMessage("normal");
    }
    /**
     * 有犯罪行动，黄埔警察出动！
     * 一切正常，不用出动！
     * =============
     * 有犯罪行动，天河警察出动！
     * 一切正常，不用出动！
     */
}
