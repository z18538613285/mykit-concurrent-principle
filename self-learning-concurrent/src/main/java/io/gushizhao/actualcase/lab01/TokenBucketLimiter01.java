package io.gushizhao.actualcase.lab01;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/27 14:30
 *
 * 令牌桶算法
 */
public class TokenBucketLimiter01 {

    // 平滑流量示例
    public static void main(String[] args) {
        // 每秒生成 5 个令牌
        RateLimiter limiter = RateLimiter.create(5);

        //返回值表示从令牌桶中获取一个令牌所花费的时间，单位是秒
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));
        System.out.println(limiter.acquire(1));

        // 0.0
        //0.199116
        //0.19775
        //0.198558
        //0.199329
        //0.199748
        //0.194887
        //0.199036
        //0.198599
        /**
         * 从输出结果可以看出：第一次从桶中获取令牌时，返回的时间为0.0，也就是没耗费时间。之后每次从桶中获取令牌时，都会耗费
         * 一定的时间，这是为什么呢？按理说，向桶中放入了5个令牌后，再从桶中获取令牌也应该和第一次一样并不会花费时间啊！
         * 因为在Guava的实现是这样的：我们使用 RateLimiter.create(5) 创建令牌桶对象时，表示每秒新增5个令牌，1秒等于1000毫
         * 秒，也就是每隔200毫秒向桶中放入一个令牌。
         */
    }
}
