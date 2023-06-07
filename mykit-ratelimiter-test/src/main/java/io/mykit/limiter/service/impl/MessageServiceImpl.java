package io.mykit.limiter.service.impl;

import io.mykit.limiter.service.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author huzhichao
 * @Description TODO
 * @Date 2023/3/27 14:57
 */
@Service
public class MessageServiceImpl implements MessageService {
    private final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public boolean sendMessage(String message) {
        logger.info("发送消息成功===>>" + message);
        return true;
    }
}
