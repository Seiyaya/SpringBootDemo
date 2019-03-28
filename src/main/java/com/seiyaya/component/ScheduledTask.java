package com.seiyaya.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduledTask {
	
	@Scheduled(fixedRate = 500000)
	public void say() {
		log.info("6666");
	}
}
