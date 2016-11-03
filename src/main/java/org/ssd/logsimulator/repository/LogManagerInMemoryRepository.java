package org.ssd.logsimulator.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import org.ssd.logsimulator.domain.LogEntry;

@Repository("LogManagerRepository")
public class LogManagerInMemoryRepository implements LogManagerRepository {

	private static final long serialVersionUID = 1116840780200953995L;
	
	private Map<Long, LogEntry> logEntriesMap = new HashMap<Long, LogEntry>();
	
	private SequencialIdGenerator idGenerator = new SequencialIdGenerator();

	@Override
	public List<LogEntry> listAll() {
		List<LogEntry> logEntriesList = new ArrayList<LogEntry>();
		logEntriesList.addAll(logEntriesMap.values());
		return logEntriesList;
	}

	@Override
	public LogEntry create(LogEntry logEntry) {
		logEntry.setId(idGenerator.getCurrentId());
		return logEntriesMap.put(logEntry.getId(), logEntry);
	}

	@Override
	public LogEntry delete(long id) {
		return logEntriesMap.remove(id);
	}

	@Override
	public LogEntry update(LogEntry logEntry) {
		return logEntriesMap.replace(logEntry.getId(), logEntry);
		
	}
	

	@Override
	public LogEntry findById(long id) {
		return logEntriesMap.get(id);
	}

	private class SequencialIdGenerator{
		
		private Long currentId = 0L;
		
		public Long getCurrentId(){
			inrcrementId();
			return currentId;
		}
		
		private synchronized void inrcrementId(){
			currentId ++;
		}
	}
}
