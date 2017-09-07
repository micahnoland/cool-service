package com.micahnoland.coolservice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micahnoland.coolservice.dto.GreetingDto;

@Controller
@RequestMapping(value = "/v1")
public class GreetingRest {
	@Value("${app.defaultName}") 
	private String defaultName;
	
	@RequestMapping(
			value = "/greeting",
			method = RequestMethod.GET, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody GreetingDto greeting(@RequestParam(value="name", required=false) String name) {
		return new GreetingDto("Hello " + (name == null ? defaultName :  name));
	}
}
