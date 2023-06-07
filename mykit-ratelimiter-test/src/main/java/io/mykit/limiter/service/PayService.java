package io.mykit.limiter.service;


import java.math.BigDecimal;

/**
 * 模拟支付
 */
public interface PayService {

    int pay(BigDecimal price);
}
