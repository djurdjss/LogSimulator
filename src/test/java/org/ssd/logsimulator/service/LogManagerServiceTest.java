package org.ssd.logsimulator.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.ssd.logsimulator.app.LogSimulatorApplication;
import org.ssd.logsimulator.domain.LogEntry;
import org.ssd.logsimulator.service.LogManagerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=LogSimulatorApplication.class)
public class LogManagerServiceTest {

	@Autowired
	private LogManagerService logManagerService;
	
	private LogEntry logEntry;
	
	@Before
	public void setup(){
		logEntry = new LogEntry();
		logEntry.setId(1L);
		logEntry.setLogData("This is test log data");
		logEntry.setOperationName("This is test operation Name");
		logEntry.setPerMinuteLogFrequency(10);
		logEntry.setUsername("dan");
		
		logManagerService.save(logEntry);

	}
	
	@After
	public void tearDown(){
		logManagerService.deleteAll();
	}

	@Test
	public void viewAll() {
		assertEquals(logManagerService.viewAll().size(),1);
	}

	@Test
	public void delete() {
		logManagerService.delete(logEntry.getId());
		assertTrue(logManagerService.viewAll().isEmpty());
	}

	@Test
	public void update() {
		//@TODO
	}

	@Test
	public void findById() {
		assertNotNull(logManagerService.findById(logEntry.getId()));
	}

	@Test
	public void simulateLogs() {
		logManagerService.simulateLogs();
	}
}
