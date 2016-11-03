package org.ssd.logsimulator.service;

import java.util.List;

import org.ssd.logsimulator.domain.LogEntry;

public interface LogManagerService {
	
	public List<LogEntry> viewAll();
	
	public LogEntry save(LogEntry logEntry);
	
	public LogEntry delete(long id);
	
	public LogEntry update(LogEntry logEntry);
	
	public LogEntry findById(long id);
	
	public void simulateLogs();
	
}
