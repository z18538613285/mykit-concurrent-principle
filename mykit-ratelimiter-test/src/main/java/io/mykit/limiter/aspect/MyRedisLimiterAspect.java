package io.mykit.limiter.aspect;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import io.mykit.limiter.annotation.MyRateLimiter;
import io.mykit.limiter.annotation.MyRedisLimiter;
import io.mykit.limiter.controller.PayController;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
public class MyRedisLimiterAspect {
    private final Logger logger = LoggerFactory.getLogger(MyRedisLimiterAspect.class);

    private RateLimiter rateLimiter = RateLimiter.create(2);

    @Autowired
    private HttpServletResponse response;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private DefaultRedisScript<List> redisScript;

    @PostConstruct
    public void init() {
        redisScript = new DefaultRedisScript<>();
        redisScript.setResultType(List.class);
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("limit.lua")));
    }

    @Pointcut("execution(public * io.mykit.limiter.controller.*.*(..))")
    public void pointcut(){}

    @Around("pointcut()")
    public Object process(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) proceedingJoinPoint.getSignature();

        // 使用反射获取方法上时候存在 @MyRateLimiter注解
        MyRedisLimiter myRedisLimiter = signature.getMethod().getDeclaredAnnotation(MyRedisLimiter.class);
        if (myRedisLimiter == null){
            // 程序正常执行，执行目标方法
            return proceedingJoinPoint.proceed();
        }
        // 获取注解上的参数
        double value = myRedisLimiter.value();

        // List 设置 Lua 的 Key[1]
        String key = "ip" + System.currentTimeMillis() / 1000;
        ArrayList<String> keyList = Lists.newArrayList(key);

        ArrayList<String> argvList = Lists.newArrayList(String.valueOf(value));

        // 调用 Lua 脚本并执行
        List result = stringRedisTemplate.execute(redisScript, keyList, String.valueOf(value));
        logger.info("Lua 脚本的执行结果:{}", result);

        //Lua脚本返回0，表示超出流量大小，返回1表示没有超出流量大小。
        if ("0".equals(result.get(0).toString())) {
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
            writer.println("回退失败，清稍后阅读");
            logger.info("回退失败，清稍后阅读");
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
