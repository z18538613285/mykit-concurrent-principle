package io.gushizhao.design.behavioralmode.cor;


/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 15:08
 *
 * ConcreteHandler
 */
public class HRRequestHandle implements RequestHandle{
    @Override
    public void handleRequest(Request request) {
        if (request instanceof DimissionRequest) {
            System.out.println("要离职，人事审批");
        }
        System.out.println("请求完毕");
    }
}
