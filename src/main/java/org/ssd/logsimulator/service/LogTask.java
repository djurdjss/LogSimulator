package org.ssd.logsimulator.service;

import org.apache.log4j.Logger;
import org.ssd.logsimulator.domain.LogEntry;

import com.fasterxml.jackson.core.JsonProcessingException;

public class LogTask implements Runnable{

	private static final int SECONDS_PER_MINUTE = 60;
	
	private static final int LOG_DURATION_IN_MINUTES = 1;
	
	private Logger logger = Logger.getLogger(this.getClass().getName());
	
	private LogEntry logEntry;
	
	public LogTask(LogEntry logEntry){
		this.logEntry = logEntry;
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
		System.out.println("Log Statements = " + logEntry.toJsonString());
	}
	
	protected void processLogStatement() throws Exception{
		for (int i = 0; i < this.getNumberOfLogsStatementsExpectedForTheTimeInterval(); i++){
			recordLogStatement(logEntry);
			Thread.sleep(getSleepTime());
		}
	}
}
