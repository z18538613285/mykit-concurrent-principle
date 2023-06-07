package io.mykit.limiter.service.impl;

import io.mykit.limiter.service.PayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/27 14:59
 */
@Service
public class PayServiceImpl implements PayService {
    private final Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Override
    public int pay(BigDecimal price) {
        logger.info("支付成功===>>", price);
        return 1;
    }
}
