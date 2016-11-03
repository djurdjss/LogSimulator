package org.ssd.logsimulator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.ssd.logsimulator.domain.LogEntry;
import org.ssd.logsimulator.repository.LogManagerRepository;

@Service("logManagerService")
public class LogMangerServiceImpl implements LogManagerService{

	private LogManagerRepository logManagerRepository;
	
	@Autowired
	public LogMangerServiceImpl(LogManagerRepository logManagerRepository){
		this.logManagerRepository = logManagerRepository;
	}
	
	@Override
	public List<LogEntry> viewAll() {
		return logManagerRepository.listAll();
	}

	@Override
	public LogEntry save(LogEntry logEntry) {
		return logManagerRepository.create(logEntry);
	}

	@Override
	public LogEntry delete(long id) {
		return logManagerRepository.delete(id);
	}

	@Override
	public LogEntry update(LogEntry logEntry) {
		return logManagerRepository.update(logEntry);
	}
	
	@Override
	public LogEntry findById(long id) {
		return logManagerRepository.findById(id);
	}

	@Override
	public void simulateLogs() {
		List<LogEntry> logEntries = logManagerRepository.listAll();
		
		for (LogEntry logEntry : logEntries){
			
		}
	}

}
