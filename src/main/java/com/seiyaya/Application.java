package com.seiyaya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.seiyaya.config.PrimaryConfig;
import com.seiyaya.config.SlaveConfig;

@SpringBootApplication
@ComponentScan(value = "com.seiyaya.*")
@EnableConfigurationProperties(value = {PrimaryConfig.class,SlaveConfig.class})
@EnableAspectJAutoProxy
@EnableScheduling
@EnableAsync
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
