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
	public void save(LogEntry logEntry) {
		logManagerRepository.create(logEntry);
	}

	@Override
	public void delete(long id) {
		logManagerRepository.delete(id);
	}

	@Override
	public void update(LogEntry logEntry) {
		logManagerRepository.update(logEntry);
	}

	@Override
	public void simulateLogs() {
		List<LogEntry> logEntries = logManagerRepository.listAll();
		
		for (LogEntry logEntry : logEntries){
			
		}
	}

}
