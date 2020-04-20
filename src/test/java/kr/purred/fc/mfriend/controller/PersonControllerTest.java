package kr.purred.fc.mfriend.controller;

import kr.purred.fc.mfriend.repository.PersonRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class PersonControllerTest
{
	@Autowired
	private PersonController personController;

	@Autowired
	private PersonRepository personRepository;

	private MockMvc mockMvc;

	@BeforeEach
	void beforeEach ()
	{
		mockMvc = MockMvcBuilders.standaloneSetup (personController).build ();
	}

	@Test
	void getPerson () throws Exception
	{
		mockMvc.perform (MockMvcRequestBuilders.get ("/api/person/1"))
			.andDo (print())
			.andExpect (status ().isOk ())
			.andExpect (jsonPath ("$.name").value ("martin"))
		;
	}

	@Test
	void postPerson () throws Exception
	{
		mockMvc.perform (MockMvcRequestBuilders.post ("/api/person")
				.contentType (MediaType.APPLICATION_JSON)
				.content ("{\n" +
						"    \"name\": \"martin2\", \"age\": \"20\", \"bloodType\": \"A\"\n" +
						"}")
		)
			.andDo (print())
			.andExpect (status ().isCreated ())
				;
	}

	@Test
	void modifyPerson () throws Exception
	{
		mockMvc.perform (MockMvcRequestBuilders.put ("/api/person/1")
				.contentType (MediaType.APPLICATION_JSON)
				.content ("{\n" +
						"    \"name\": \"martin2\", \"age\": \"20\", \"bloodType\": \"A\"\n" +
						"}")
		)
				.andDo (print())
				.andExpect (status ().isOk ())
		;
	}

	@Test
	void modifyPersonName () throws Exception
	{
		mockMvc.perform (MockMvcRequestBuilders.patch ("/api/person/1")
				.param ("name", "martrin3")
		)
				.andDo (print())
				.andExpect (status ().isOk ())
		;
	}

	@Test
	void deletePersonTest () throws Exception
	{
		mockMvc.perform (MockMvcRequestBuilders.delete ("/api/person/1"))
				.andDo (print())
				.andExpect (status ().isOk ())
		;
	}
}