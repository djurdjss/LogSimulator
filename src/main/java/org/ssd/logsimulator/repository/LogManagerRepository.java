package org.ssd.logsimulator.repository;

import java.io.Serializable;
import java.util.List;

import org.ssd.logsimulator.domain.LogEntry;

public interface LogManagerRepository extends Serializable {
	
	public List<LogEntry> listAll();
	
	public void create(LogEntry logEntry);
	
	public void delete(long id);
	
	public void update(LogEntry logEntry);
	
}
