package org.ssd.logsimulator.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ssd.logsimulator.service.LogManagerService;
import org.ssd.web.common.RestServiceResponse;

@RestController
public class LogMonitorController {

	private LogManagerService logManagerService;
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	@Autowired
	public LogMonitorController(LogManagerService logManagerService){
		logger.info("creating a logger");
		this.logManagerService = logManagerService;
	}

	@RequestMapping(value = "/monitor", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestServiceResponse> displayAllFlagedLogEntries(){
		List<String> logEntries = logManagerService.displayFlagedLogs();
		return new ResponseEntity<RestServiceResponse>(new RestServiceResponse(logEntries), HttpStatus.OK);
	}
}
