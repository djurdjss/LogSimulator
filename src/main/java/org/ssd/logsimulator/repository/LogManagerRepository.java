package org.ssd.logsimulator.repository;

import java.io.Serializable;
import java.util.List;

import org.ssd.logsimulator.domain.LogEntry;

public interface LogManagerRepository extends Serializable {
	
	public List<LogEntry> listAll();
	
	public LogEntry create(LogEntry logEntry);
	
	public LogEntry delete(long id);
	
	public LogEntry update(LogEntry logEntry);
	
	public LogEntry findById(long id);
	
	public void deleteAll();
	
}
