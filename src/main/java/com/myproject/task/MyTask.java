package com.myproject.task;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.myproject.util.BaseUtil;

@Component
public class MyTask {
	
	private static final Logger logger = LoggerFactory.getLogger(MyTask.class);
	
	@Scheduled(cron="59 * * * * ?")
	public void print(){
		logger.info("定时任务执行中....."+BaseUtil.date2String(new Date()));
	}
}
