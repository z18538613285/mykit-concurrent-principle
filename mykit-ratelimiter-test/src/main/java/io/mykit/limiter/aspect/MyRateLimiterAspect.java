package io.mykit.limiter.aspect;

import com.google.common.util.concurrent.RateLimiter;
import io.mykit.limiter.annotation.MyRateLimiter;
import io.mykit.limiter.controller.PayController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/27 15:22
 *
 * 一般限流切面类
 */
@Aspect
@Component
public class MyRateLimiterAspect {
    private final Logger logger = LoggerFactory.getLogger(MyRateLimiterAspect.class);

    private RateLimiter rateLimiter = RateLimiter.create(2);

    @Autowired
    private HttpServletResponse response;

    @Pointcut("execution(public * io.mykit.limiter.controller.*.*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        // 使用反射获取方法上时候存在 @MyRateLimiter注解
        MyRateLimiter myRateLimiter = signature.getMethod().getDeclaredAnnotation(MyRateLimiter.class);
        if (myRateLimiter == null){
            // 程序正常执行，执行目标方法
            return proceedingJoinPoint.proceed();
        }
        // 获取注解上的参数
        double rate = myRateLimiter.rate();
        long timeout = myRateLimiter.timeout();

        // 设置限流速率
        rateLimiter.setRate(rate);
        boolean tryAcquire = rateLimiter.tryAcquire(timeout, TimeUnit.MILLISECONDS);
        if (!tryAcquire) {
            // 服务降级
            fullback();
            return null;
        }

        return proceedingJoinPoint.proceed();

    }
    // 服务降级
    private void fullback() {
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.println("出错了，重试一次试试？");
            logger.info("出错了，重试一次试试？");
            writer.flush();;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(writer != null){
                writer.close();
            }
        }
    }

}
