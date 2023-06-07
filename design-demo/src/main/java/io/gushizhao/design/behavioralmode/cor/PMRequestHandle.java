package io.gushizhao.design.behavioralmode.cor;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 15:15
 *
 * ConcreteHandler
 */
public class PMRequestHandle implements RequestHandle{

    RequestHandle rh;

    public PMRequestHandle(RequestHandle rh) {
        this.rh = rh;
    }

    @Override
    public void handleRequest(Request request) {
        if (request instanceof AddMoneyRequest) {
            System.out.println("要加薪，项目经理审批！");
        } else {
            rh.handleRequest(request);
        }
    }
}
