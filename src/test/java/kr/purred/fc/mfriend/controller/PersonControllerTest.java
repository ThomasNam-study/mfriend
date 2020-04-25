package kr.purred.fc.mfriend.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.purred.fc.mfriend.domain.Person;
import kr.purred.fc.mfriend.dto.PersonDto;
import kr.purred.fc.mfriend.handler.GlobalExceptionHandler;
import kr.purred.fc.mfriend.repository.PersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class PersonControllerTest
{
	@Autowired
	private PersonController personController;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	ObjectMapper objectMapper;

	@Autowired
	GlobalExceptionHandler globalExceptionHandler;

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	private MappingJackson2HttpMessageConverter messageConverter;

	@BeforeEach
	void beforeEach ()
	{
		/*mockMvc = MockMvcBuilders.standaloneSetup (personController)
				.setMessageConverters (messageConverter)
				.setControllerAdvice(globalExceptionHandler)
				.alwaysDo(print())
				.build ();*/

		mockMvc = MockMvcBuilders.webAppContextSetup (wac)
				.alwaysDo(print())
				.build ();
	}

	@Test
	void getPerson () throws Exception
	{
		mockMvc.perform (MockMvcRequestBuilders.get ("/api/person/1"))
				//.andDo (print())
				.andExpect (status ().isOk ())
				.andExpect (jsonPath ("$.name").value ("martin"))
				.andExpect (jsonPath ("$.hobby").isEmpty ())
				.andExpect (jsonPath ("$.address").isEmpty ())
				.andExpect (jsonPath ("$.birthday").value ("1991-08-15"))
				.andExpect (jsonPath ("$.job").value ("Programmer"))
				.andExpect (jsonPath ("$.phoneNumber").isEmpty ())
				.andExpect (jsonPath ("$.deleted").value (false))
		;
	}

	@Test
	void postPerson () throws Exception
	{
		PersonDto dto = new PersonDto();

		dto.setName("martin");
		dto.setBirthday(LocalDate.now());
		dto.setAddress("Seoul");
		dto.setHobby("Programming");
		dto.setPhoneNumber("010-5349-6254");

		mockMvc.perform (MockMvcRequestBuilders.post ("/api/person")
				.contentType (MediaType.APPLICATION_JSON)
				.content (toJsonString(dto))
		)
			.andExpect (status ().isCreated ())
		;

		Person person = personRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).get(0);

		assertAll(
				() -> Assertions.assertThat(person.getName ()).isEqualTo("martin"),
				() -> Assertions.assertThat(person.getHobby ()).isEqualTo("Programming"),
				() -> Assertions.assertThat(person.getAddress ()).isEqualTo("Seoul")
		);
	}

	@Test
	void postPersonIfNotException () throws Exception
	{
		PersonDto dto = new PersonDto();

		mockMvc.perform (MockMvcRequestBuilders.post ("/api/person")
				.contentType (MediaType.APPLICATION_JSON_UTF8)
				.content (toJsonString(dto))
		)
				.andExpect (status ().isInternalServerError ())
				.andExpect(jsonPath("$.code").value(500))
		;

	}

	@Test
	void modifyPerson () throws Exception
	{
		PersonDto dto = new PersonDto();

		dto.setName("martin");
		dto.setBirthday(LocalDate.now());
		dto.setAddress("Seoul");
		dto.setHobby("Programming");
		dto.setPhoneNumber("010-5349-6254");

		mockMvc.perform (MockMvcRequestBuilders.put ("/api/person/1")
				.contentType (MediaType.APPLICATION_JSON_UTF8)
				.content (toJsonString(dto))
		)
				//.andDo (print())
				.andExpect (status ().isOk ())
		;

		long id = 1;

		Optional<Person> byId = personRepository.findById(id);

		if (!byId.isPresent())
			Assertions.fail("데이터 없음");
		else
		{
			Person person = byId.get();

			assertAll(
					() -> Assertions.assertThat(person.getName ()).isEqualTo("martin22"),
					() -> Assertions.assertThat(person.getHobby ()).isEqualTo("fff"),
					() -> Assertions.assertThat(person.getAddress ()).isEqualTo("sss")
			);
			/*assertTrue(personRepository.findById(id).get().getName().equals(dto.getName()));
			assertTrue(personRepository.findById(id).get().getHobby().equals(dto.getHobby()));
			assertTrue(personRepository.findById(id).get().getAddress().equals(dto.getAddress()));*/


		}


	}

	@Test
	void modifyPersonDiff () throws Exception
	{
		PersonDto dto = new PersonDto();

		dto.setName("martin5");
		dto.setBirthday(LocalDate.now());
		dto.setAddress("Seoul");
		dto.setHobby("Programming");
		dto.setPhoneNumber("010-5349-6254");

		//assertThrows(NestedServletException.class, () -> {
			mockMvc.perform(MockMvcRequestBuilders.put("/api/person/1")
					.contentType(MediaType.APPLICATION_JSON_UTF8)
					.content(toJsonString(dto))
			)
				//	.andDo(print())
					.andExpect(status().isBadRequest())
					.andExpect(jsonPath("$.code").value(400))
			;
		//});
	}

	@Test
	void modifyPersonIfPersonNotFound () throws Exception {
		PersonDto dto = new PersonDto();

		dto.setName("martin");
		dto.setBirthday(LocalDate.now());
		dto.setAddress("Seoul");
		dto.setHobby("Programming");
		dto.setPhoneNumber("010-5349-6254");

		mockMvc.perform(MockMvcRequestBuilders.put("/api/person/10")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(toJsonString(dto))
		)
				// .andDo(print())
				.andExpect(status().isBadRequest())
				.andExpect(jsonPath("$.code").value(400))
		;
	}

	@Test
	void modifyPersonName () throws Exception
	{
		long id = 1;

		String modifyName = "martrin3";

		mockMvc.perform (MockMvcRequestBuilders.patch ("/api/person/" + id)
				.param ("name", modifyName)
		)
				//.andDo (print())
				.andExpect (status ().isOk ())
		;

		assertTrue(personRepository.findById(id).get().getName().equals(modifyName));
	}

	@Test
	void deletePersonTest () throws Exception
	{
		long id = 1;

		mockMvc.perform (MockMvcRequestBuilders.delete ("/api/person/" + id))
				//.andDo (print())
				.andExpect (status ().isOk ())
		;

		// System.out.println(personRepository.findDeletePeople());
		assertTrue(personRepository.findDeletePeople().stream().anyMatch((p) -> p.getId().equals(id)));
	}

	private String toJsonString (PersonDto personDto) throws JsonProcessingException {
		return objectMapper.writeValueAsString(personDto);
	}
}