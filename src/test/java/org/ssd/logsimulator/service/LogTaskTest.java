package org.ssd.logsimulator.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.ssd.logsimulator.domain.LogEntry;

import com.fasterxml.jackson.core.JsonProcessingException;

public class LogTaskTest {

	private TestLogTask testLogTask;
	
	private LogTask logTask;
	
	private LogEntry logEntry;
	
	@Before
	public void setup(){
		logEntry = new LogEntry();
		logEntry.setId(1L);
		logEntry.setLogData("This is test log data");
		logEntry.setOperationName("This is test operation Name");
		logEntry.setPerMinuteLogFrequency(10);
		logEntry.setUsername("dan");
		
		testLogTask = new TestLogTask(logEntry);
		logTask = new LogTask(logEntry);
		
	}
	
	@Test
	public void getSleepTime(){
		assertEquals(logTask.getSleepTime(), 6000);
	}
	
	@Test
	public void getNumberOfLogsStatementsExpectedForTheTimeInterval(){
		assertEquals(logTask.getNumberOfLogsStatementsExpectedForTheTimeInterval(), 10);
	}
	
	@Test
	public void run() throws Exception{
		testLogTask.run();
		assertEquals(testLogTask.getNumberOfLogStatements(), logTask.getNumberOfLogsStatementsExpectedForTheTimeInterval());
	}
	
	private class TestLogTask extends LogTask{

		private List<String> logEntries = null;
		
		public TestLogTask(LogEntry logEntry) {
			super(logEntry);
			logEntries = new ArrayList<String>();
		}
		
		@Override
		protected int getSleepTime(){
			return 0;
		}
		
		@Override
		protected void recordLogStatement(LogEntry logEntry) throws JsonProcessingException{
			logEntries.add(logEntry.toJsonString());
		}
		
		public int getNumberOfLogStatements(){
			return logEntries.size();
		}
	}
}
