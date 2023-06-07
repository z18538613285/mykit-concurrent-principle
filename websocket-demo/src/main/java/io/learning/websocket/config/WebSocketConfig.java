package io.learning.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

@Configuration
public class WebSocketConfig {
	
	/**
	 * 注入websocket的bean，不过这种直接创建对象的方式，
	 * 应该不是一个效率好的方式，不过这里作为演示是足够了。
	 * */
	@Bean
	public ServerEndpointExporter serverEndpointExporter() {
		return new ServerEndpointExporter();
	}
	
}
