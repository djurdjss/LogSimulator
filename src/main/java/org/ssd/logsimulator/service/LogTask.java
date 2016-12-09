package org.ssd.logsimulator.service;

import java.util.UUID;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.redis.core.RedisTemplate;
import org.ssd.logsimulator.domain.LogEntry;

import com.fasterxml.jackson.core.JsonProcessingException;

public class LogTask implements Runnable{

	private static final int SECONDS_PER_MINUTE = 60;
	
	private static final int LOG_DURATION_IN_MINUTES = 3;
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	private LogEntry logEntry;
	
	private RedisTemplate<String,String> redisTemplate;
	
	public LogTask(LogEntry logEntry,RedisTemplate<String,String> redisTemplate){
		this.logEntry = logEntry;
		this.redisTemplate = redisTemplate;
	}
	
	@Override
	public void run() {
		try{
			processLogStatement();
		}catch(Exception e){
			throw new RuntimeException(
					"Exception occured in LogTask:" + e.getMessage(),e);
		}
	}
	
	protected int getSleepTime(){
		return SECONDS_PER_MINUTE / this.logEntry.getPerMinuteLogFrequency() * 1000;
	}
	
	protected int getNumberOfLogsStatementsExpectedForTheTimeInterval(){
		return LOG_DURATION_IN_MINUTES * this.logEntry.getPerMinuteLogFrequency();
	}
	
	protected void recordLogStatement(LogEntry logEntry) throws JsonProcessingException{
		logger.info(logEntry.toJsonString());
		String key = "SIM1" + UUID.randomUUID().toString();
		String value = logEntry.toJsonString(); 
		redisTemplate.opsForValue().set(key, value);
		System.out.println("Log Statements = " + key + ":" + value);
	}
	
	protected void processLogStatement() throws Exception{
		for (int i = 0; i < this.getNumberOfLogsStatementsExpectedForTheTimeInterval(); i++){
			recordLogStatement(logEntry);
			Thread.sleep(getSleepTime());
		}
	}
}
