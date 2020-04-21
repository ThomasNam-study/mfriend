package kr.purred.fc.mfriend.configuration.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import kr.purred.fc.mfriend.domain.dto.Birthday;

import java.io.IOException;
import java.time.LocalDate;

public class BirthdaySerializer extends JsonSerializer<Birthday>
{
	@Override
	public void serialize (Birthday value, JsonGenerator gen, SerializerProvider serializers) throws IOException
	{
		if (value != null)
			gen.writeObject (LocalDate.of (value.getYear (), value.getMonth (), value.getDay ()));
	}
}
