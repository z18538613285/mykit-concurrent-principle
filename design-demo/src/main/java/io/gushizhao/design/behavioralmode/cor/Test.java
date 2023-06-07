package io.gushizhao.design.behavioralmode.cor;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/4/1 15:18
 */
public class Test {
    public static void main(String[] args) {
        RequestHandle hr = new HRRequestHandle();
        RequestHandle pm = new PMRequestHandle(hr);
        RequestHandle tl = new TLRequestHandle(pm);

        //team leader处理离职请求
        Request request = new Request();
        tl.handleRequest(request);
        System.out.println("===========");
        //team leader处理加薪请求
        request = new AddMoneyRequest();
        tl.handleRequest(request);
        System.out.println("========");
        //项目经理上理辞职请求
        request = new DimissionRequest();
        pm.handleRequest(request);
    }
    /**
     * 请求完毕
     * ===========
     * 要加薪，项目经理审批！
     * ========
     * 要离职，人事审批
     * 请求完毕
     */
}
