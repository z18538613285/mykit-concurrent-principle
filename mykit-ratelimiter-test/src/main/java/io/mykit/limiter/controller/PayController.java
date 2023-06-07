package io.mykit.limiter.controller;

import com.google.common.util.concurrent.RateLimiter;
import io.mykit.limiter.annotation.MyRateLimiter;
import io.mykit.limiter.annotation.MyRedisLimiter;
import io.mykit.limiter.service.MessageService;
import io.mykit.limiter.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/27 15:00
 * 测试接口限流
 *
 * 在PayController类的接口pay()方法中使用了限流，每秒钟向桶中放入2个
 * 令牌，并且客户端从桶中获取令牌，如果在500毫秒内没有获取到令牌的话，我们可以则直接走服务降级处理。
 */
@RestController
public class PayController {
    private final Logger logger = LoggerFactory.getLogger(PayController.class);

    /**
     * RateLimiter的create()方法中传入一个参数，表示以固定的速率2r/s，即以每秒2个令牌的速率向桶中放入令牌
     */
    private RateLimiter rateLimiter = RateLimiter.create(2);

    @Autowired
    private MessageService messageService;
    @Autowired
    private PayService payService;

    // 不使用注解方式实现限流的Web应用就基本完成了。
    @RequestMapping("/boot/pay")
    public String pay() {
        // 记录返回接口
        String result = "";
        // 限流处理，客户端请求从桶中获取令牌，如果在500毫秒没有获取到令牌，则直接走服务降级处理
        boolean tryAcquire = rateLimiter.tryAcquire(500, TimeUnit.MILLISECONDS);
        if (!tryAcquire) {
            result = "请求过多，降级处理";
            logger.info(result);
            return result;
        }
        int ret = payService.pay(BigDecimal.valueOf(100.0));
        if (ret > 0) {
            result = "支付成功";
            return result;
        }
        result = "支付失败，再试一次吧...";
        return result;
    }

    @RequestMapping("/boot/send/message")
    @MyRateLimiter(rate = 1.0, timeout = 500)
    public String sendMessage(){
        //记录返回接口
        String result = "";
        boolean flag = messageService.sendMessage("恭喜您成长值+1");
        if (flag){
            result = "消息发送成功";
            return result;
        }
        result = "消息发送失败，再试一次吧...";
        return result;
    }

    /**
     * 基于限流算法实现限流的缺点
     * 上面介绍的限流方式都只能用于单机部署的环境中，如果将应用部署到多台服务器进行分布式、集群，则上面限流的方式就不适
     * 用了，此时，我们需要使用分布式限流。
     */


    @RequestMapping("/boot/send/message2")
    @MyRedisLimiter(value = 10)
    public String sendMessage2(){
        //记录返回接口
        String result = "";
        boolean flag = messageService.sendMessage("恭喜您成长值+1");
        if (flag){
            result = "短信发送成功";
            return result;
        }
        result = "短信发送失败，再试一次吧...";
        return result;
    }

}
