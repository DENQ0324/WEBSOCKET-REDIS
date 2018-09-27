package com.lensyn.ispbs;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.lensyn.ispbs.system.ws.WebSocketRainServer;

@SpringBootApplication
@EnableScheduling
@MapperScan("com.lensyn.ispbs.mapper")
public class IspbsApplication {

	public static void main(String[] args) {
		
		SpringApplication springApplication = new SpringApplication(IspbsApplication.class);
		   ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
		   //解决WebSocket不能注入的问题
		   WebSocketRainServer.setApplicationContext(configurableApplicationContext);
		//SpringApplication.run(IspbsApplication.class, args);
	}
}
