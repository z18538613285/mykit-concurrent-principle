package io.mykit.limiter.annotation;

import java.lang.annotation.*;

/**
 * 实现限流的自定义注解
 */
@Target(value = ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyRateLimiter {
    // 向令牌桶放入令牌的速率
    double rate();
    // 从令牌桶总获取令牌的超时时间
    long timeout() default 0;
}
