package com.psalguero.test.testcontroller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HellowResource {

	@GetMapping
	public String hellow() {
		return "Patrick Salguero";
	}
	
	@RequestMapping(value= "/json", method= RequestMethod.GET, produces= {  } )
	public Hellow hellowJson() {
		return new Hellow("nombre", "Patrick Salguero");
	}
	
}
