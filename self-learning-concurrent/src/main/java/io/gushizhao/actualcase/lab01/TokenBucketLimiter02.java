package io.gushizhao.actualcase.lab01;

import com.google.common.util.concurrent.RateLimiter;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/27 14:30
 *
 * 令牌桶算法
 *
 * Guava令牌桶算法的特点
 * RateLimiter使用令牌桶算法，会进行令牌的累积，如果获取令牌的频率比较低，则不会导致等待，直接获取令牌。
 * RateLimiter由于会累积令牌，所以可以应对突发流量。也就是说如果同时请求5个令牌，由于此时令牌桶中有累积的令牌，
 * 能够快速响应请求。
 * RateLimiter在没有足够的令牌发放时，采用的是滞后的方式进行处理，也就是前一个请求获取令牌所需要等待的时间由下
 * 一次请求来承受和弥补，也就是代替前一个请求进行等待。（这里，小伙伴们要好好理解下）
 */
public class TokenBucketLimiter02 {

    // 突发流量示例
    public static void main(String[] args) {
        // 每秒生成 5 个令牌
        RateLimiter limiter = RateLimiter.create(5);

        //返回值表示从令牌桶中获取一个令牌所花费的时间，单位是秒
        // 上述代码表示的含义为：每秒向桶中放入5个令牌，第一次从桶中获取50个令牌，也就是我们说的突发流量，后续每次从桶中获
        //取5个令牌。
        System.out.println(limiter.acquire(50));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));
        System.out.println(limiter.acquire(5));

        /**
         * 程序每秒钟向桶中放入5个令牌，当程序运行到 RateLimiter limiter = RateLimiter.create(5); 时，就会向桶中放入令
         * 牌。当运行到 System.out.println(limiter.acquire(50)); 时，发现很快就会获取到令牌，花费了0.0秒。接下来，运行到
         * 第一个 System.out.println(limiter.acquire(5)); 时，花费了9.998409秒。小伙们可以思考下，为什么这里会花费10秒中
         * 的时间呢？
         * 这是因为我们使用 RateLimiter limiter = RateLimiter.create(5); 代码向桶中放入令牌时，一秒钟放入5个，而
         * System.out.println(limiter.acquire(50)); 需要获取50个令牌，也就是获取50个令牌需要花费10秒钟时间，这是因为程序
         * 向桶中放入50个令牌需要10秒钟。程序第一次从桶中获取令牌时，很快就获取到了。而第二次获取令牌时，花费了将近10秒的时
         * 间。
         */

        /**
         * Guava框架支持突发流量，但是在突发流量之后再次请求时，会被限速，也就是说：在突发流量之后，再次请求时，会弥补处理
         * 突发请求所花费的时间。所以，我们的突发示例程序中，在一次从桶中获取50个令牌后，再次从桶中获取令牌，则会花费10秒左
         * 右的时间。
         */
    }
}
