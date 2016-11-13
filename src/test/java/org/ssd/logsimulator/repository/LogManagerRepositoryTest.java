package org.ssd.logsimulator.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.ssd.logsimulator.domain.LogEntry;

public class LogManagerRepositoryTest{

	private LogManagerRepository logManagerRepository;
	
	private LogEntry logEntryToCreate;

	@Before
	public void setup(){
		logManagerRepository = new LogManagerInMemoryRepository();
		logEntryToCreate = new LogEntry();
		logManagerRepository.create(logEntryToCreate);
	}
	
	@Test
	public void listAll() {
		assertEquals(logManagerRepository.listAll().size(),1);
	}

	@Test
	public void delete() {
		LogEntry deletedLogEntry = logManagerRepository.delete(logEntryToCreate.getId());
		assertEquals(deletedLogEntry.getId(), logEntryToCreate.getId());
		assertTrue(logManagerRepository.listAll().isEmpty());
	}

	@Test
	public void deleteAll(){
		logManagerRepository.deleteAll();
		assertTrue(logManagerRepository.listAll().isEmpty());
		
	}
	@Test
	public void update() {
		//@TODO: add this test
	}

	@Test
	public void findById() {
		LogEntry logEntry = logManagerRepository.findById(logEntryToCreate.getId());
		assertEquals(logEntry.getId(), logEntryToCreate.getId());
	}
}
