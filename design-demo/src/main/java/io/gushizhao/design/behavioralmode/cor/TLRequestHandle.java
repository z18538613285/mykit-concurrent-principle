package io.gushizhao.design.behavioralmode.cor;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 15:15
 *
 * ConcreteHandler
 */
public class TLRequestHandle implements RequestHandle{

    RequestHandle rh;

    public TLRequestHandle(RequestHandle rh) {
        this.rh = rh;
    }

    @Override
    public void handleRequest(Request request) {
        if (request instanceof LeaveRequest) {
            System.out.println("要请假，项目组长审批！");
        } else {
            rh.handleRequest(request);
        }
    }
}
