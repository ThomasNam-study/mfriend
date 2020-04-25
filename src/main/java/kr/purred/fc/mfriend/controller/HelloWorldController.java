package kr.purred.fc.mfriend.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloWorldController
{
	@GetMapping("/api/helloWorld")
	public String helloWorld ()
	{
		return "Hello World";
	}

	@GetMapping("/api/helloException")
	public String helloWorldException ()
	{
		throw new RuntimeException("Hello!@!!");
	}


}
