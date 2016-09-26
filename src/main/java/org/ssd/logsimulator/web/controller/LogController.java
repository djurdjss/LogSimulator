package org.ssd.logsimulator.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

	
	@RequestMapping(value="/log",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public String home(){
		return "This is a home page";
	}
}
