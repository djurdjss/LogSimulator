package org.ssd.logsimulator.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.ssd.logsimulator.domain.LogEntry;
import org.ssd.logsimulator.repository.LogManagerRepository;

@Service("logManagerService")
public class LogMangerServiceImpl implements LogManagerService{

	private LogManagerRepository logManagerRepository;
	
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	@Autowired
	public LogMangerServiceImpl(LogManagerRepository logManagerRepository,
			ThreadPoolTaskExecutor threadPoolTaskExecutor){
		this.logManagerRepository = logManagerRepository;
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
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
	public void deleteAll() {
		logManagerRepository.deleteAll();
	}

	@Override
	public void simulateLogs() {
		List<LogEntry> logEntries = logManagerRepository.listAll();
		
		for (LogEntry logEntry : logEntries){
			threadPoolTaskExecutor.execute(new LogTask(logEntry));
		}
		
		while (threadPoolTaskExecutor.getActiveCount() > 0){
//			System.out.println("Active Threads = " + threadPoolTaskExecutor.getActiveCount());
		}
		
//		threadPoolTaskExecutor.shutdown();
		
	}

}
