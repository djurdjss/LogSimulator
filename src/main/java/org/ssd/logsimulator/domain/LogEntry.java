package org.ssd.logsimulator.domain;

import java.io.Serializable;

public class LogEntry implements Serializable {

	private static final long serialVersionUID = 6140949891635541048L;

	private Long id;
	
	private String username;
	
	private String operationName;
	
	private String logData;
	
	private int perMinuteLogFrequency;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public String getLogData() {
		return logData;
	}

	public void setLogData(String logData) {
		this.logData = logData;
	}

	public int getPerMinuteLogFrequency() {
		return perMinuteLogFrequency;
	}

	public void setPerMinuteLogFrequency(int perMinuteLogFrequency) {
		this.perMinuteLogFrequency = perMinuteLogFrequency;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
