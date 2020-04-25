package kr.purred.fc.mfriend.controller;

import kr.purred.fc.mfriend.handler.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
class HelloWorldControllerTest
{
	@Autowired
	private HelloWorldController helloWorldController;

	private MockMvc mockMvc;

	@Autowired
	GlobalExceptionHandler globalExceptionHandler;

	@Autowired
	private WebApplicationContext wac;

	@BeforeEach
	void beforeEach ()
	{
		/*mockMvc = MockMvcBuilders.standaloneSetup (helloWorldController)
				.alwaysDo(print())
				.setControllerAdvice(globalExceptionHandler)
				.build ();*/

		mockMvc = MockMvcBuilders.webAppContextSetup (wac)
				.alwaysDo(print())
				.build ();
	}

	@Test
	void helloWorld ()
	{
		assertThat(helloWorldController.helloWorld ()).isEqualTo ("Hello World");
	}

	@Test
	void mockMvcTest () throws Exception
	{
		mockMvc.perform (
				MockMvcRequestBuilders.get ("/api/helloWorld")
		)
		.andDo (print ())
		.andExpect (MockMvcResultMatchers.status ().isOk ())
		.andExpect (MockMvcResultMatchers.content ().string ("Hello World"))
		;
	}

	@Test
	void mockMvcTestException () throws Exception
	{
		mockMvc.perform (
				MockMvcRequestBuilders.get ("/api/helloException")
		)
				.andDo (print ())
				.andExpect (MockMvcResultMatchers.status ().isInternalServerError ())
				.andExpect(jsonPath("$.code").value(500))
		;
	}
}