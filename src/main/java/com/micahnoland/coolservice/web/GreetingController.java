package com.micahnoland.coolservice.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GreetingController {
	@Value("${app.defaultName}")
	private String defaultName;
	
	@Value("${ACTIVE_PROFILES}")
	private String appEnv;
	
	@Value("${MY_SECRET}")
	private String secret;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(@RequestParam(value="name", required=false) String name, Model model) {
		model.addAttribute("name", name == null ? defaultName : name);
		model.addAttribute("appEnv", appEnv);
		model.addAttribute("secret", secret);
		return "index";
	}
}