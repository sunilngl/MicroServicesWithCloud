package com.clienttwo.app;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientTwoController {
	
	@RequestMapping(value = "/")
	public String home() {
	   return "Eureka Client Two application is running.....";
	 }

}
