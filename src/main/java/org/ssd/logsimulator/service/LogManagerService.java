package org.ssd.logsimulator.service;

import java.util.List;

import org.ssd.logsimulator.domain.LogEntry;

public interface LogManagerService {
	
	public List<LogEntry> viewAll();
	
	public void save(LogEntry logEntry);
	
	public void delete(long id);
	
	public void update(LogEntry logEntry);
	
	public void simulateLogs();
	
}
