package org.ssd.logsimulator.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;
import org.ssd.logsimulator.domain.LogEntry;
import org.ssd.logsimulator.repository.LogManagerRepository;

@Service("logManagerService")
public class LogMangerServiceImpl implements LogManagerService{

	private LogManagerRepository logManagerRepository;
	
	private ThreadPoolTaskExecutor threadPoolTaskExecutor;
	
	private RedisTemplate<String,String> redisTemplate;
	
	@Autowired
	public LogMangerServiceImpl(LogManagerRepository logManagerRepository,
			ThreadPoolTaskExecutor threadPoolTaskExecutor, RedisTemplate<String,String> redisTemplate){
		this.logManagerRepository = logManagerRepository;
		this.threadPoolTaskExecutor = threadPoolTaskExecutor;
		this.redisTemplate = redisTemplate;
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
			threadPoolTaskExecutor.execute(new LogTask(logEntry, redisTemplate));
		}
	}

	@Override
	public List<String> displayFlagedLogs() {
		Set<String> keys = redisTemplate.keys("BAD_SIM1*");
		return redisTemplate.opsForValue().multiGet(keys);
	}
}
