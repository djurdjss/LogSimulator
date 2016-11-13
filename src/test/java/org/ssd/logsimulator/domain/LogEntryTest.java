package org.ssd.logsimulator.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class LogEntryTest{

	private LogEntry logEntry;
	
	@Before
	public void setup(){
		logEntry = new LogEntry();
		logEntry.setId(0L);
		logEntry.setLogData("This is a test data");
		logEntry.setOperationName("Test operation");
		logEntry.setPerMinuteLogFrequency(10);
		logEntry.setUsername("TestUser");
	}
	
	@Test
	public void logEntryAsJsonString() throws Exception{
		ObjectMapper objectMapper = new ObjectMapper();
		String logEntryJson = logEntry.toJsonString();
		LogEntry logEntryFromJson = objectMapper.readValue(logEntryJson, LogEntry.class);
		
		assertEquals(logEntryFromJson.getId(),logEntry.getId());
		assertEquals(logEntryFromJson.getLogData(),logEntry.getLogData());
		assertEquals(logEntryFromJson.getOperationName(),logEntry.getOperationName());
		assertEquals(logEntryFromJson.getPerMinuteLogFrequency(),logEntry.getPerMinuteLogFrequency());
		assertEquals(logEntryFromJson.getUsername(),logEntry.getUsername());
	}
}
