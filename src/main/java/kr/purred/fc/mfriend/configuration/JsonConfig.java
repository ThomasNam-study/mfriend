package kr.purred.fc.mfriend.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import kr.purred.fc.mfriend.configuration.serializer.BirthdaySerializer;
import kr.purred.fc.mfriend.domain.dto.Birthday;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JsonConfig
{
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter (ObjectMapper om)
	{
		MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter ();

		converter.setObjectMapper (om);

		return converter;
	}

	@Bean
	public ObjectMapper objectMapper ()
	{
		ObjectMapper objectMapper = new ObjectMapper ();

		objectMapper.registerModule (new BirthdayModule ());
		objectMapper.registerModule (new JavaTimeModule ());

		objectMapper.configure (SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

		return objectMapper;
	}

	static class BirthdayModule extends SimpleModule
	{
		public BirthdayModule ()
		{
			super();

			addSerializer (Birthday.class, new BirthdaySerializer ());
		}
	}
}
