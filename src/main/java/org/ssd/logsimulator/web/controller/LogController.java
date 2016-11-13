package org.ssd.logsimulator.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.ssd.logsimulator.domain.LogEntry;
import org.ssd.logsimulator.service.LogManagerService;
import org.ssd.web.common.RestServiceResponse;

@RestController
@RequestMapping("/log")
public class LogController {

	private LogManagerService logManagerService;
	
	private Logger logger = LogManager.getLogger(this.getClass().getName());
	
	
	@Autowired
	public LogController(LogManagerService logManagerService){
		logger.info("creating a logger");
		this.logManagerService = logManagerService;
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestServiceResponse> displayLogEntryWithId(@PathVariable("id") Long id){
		LogEntry logEntry = logManagerService.findById(id);
		return new ResponseEntity<RestServiceResponse>(new RestServiceResponse(logEntry), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestServiceResponse> displayAllLogEntries(){
		List<LogEntry> logEntries = logManagerService.viewAll();
		return new ResponseEntity<RestServiceResponse>(new RestServiceResponse(logEntries), HttpStatus.OK);
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestServiceResponse> createlogEntry(@RequestBody LogEntry logEntry){
		return new ResponseEntity<RestServiceResponse>(new RestServiceResponse(logManagerService.save(logEntry)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestServiceResponse> updatelogEntry(@RequestBody LogEntry logEntry){
		return new ResponseEntity<RestServiceResponse>(new RestServiceResponse(logManagerService.update(logEntry)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/delete",method=RequestMethod.DELETE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<RestServiceResponse> deltelogEntry(@PathVariable("id") Long id){
		return new ResponseEntity<RestServiceResponse>(new RestServiceResponse(logManagerService.delete(id)), HttpStatus.OK);
	}
	
	@RequestMapping(value="/simulate",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	public void simulateLogs(){
		logManagerService.simulateLogs();
	}
}