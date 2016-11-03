package org.ssd.logsimulator.service;

import org.ssd.logsimulator.domain.LogEntry;

public interface LogEntryProcessor {

	public void processLogEntry(LogEntry logEntry);
}
