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
	public void create(LogEntry logEntry) {
		logEntry.setId(idGenerator.getCurrentId());
		logEntriesMap.put(logEntry.getId(), logEntry);
		
	}

	@Override
	public void delete(long id) {
		logEntriesMap.remove(id);
		
	}

	@Override
	public void update(LogEntry logEntry) {
		logEntriesMap.replace(logEntry.getId(), logEntry);
		
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
