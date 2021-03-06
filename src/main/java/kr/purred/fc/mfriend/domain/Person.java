package kr.purred.fc.mfriend.domain;

import kr.purred.fc.mfriend.domain.dto.Birthday;
import kr.purred.fc.mfriend.dto.PersonDto;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Where;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
@Where(clause = "deleted = false")
public class Person
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@NotEmpty
	@Column(nullable = false)
	private String name;

	private String hobby;

	private String address;

	// private LocalDate birthday;
	@Embedded
	@Valid
	private Birthday birthday;

	private String job;

	private String phoneNumber;

	@ColumnDefault ("0")
	private boolean deleted;

	public void set (PersonDto dto)
	{
		if (!StringUtils.isEmpty (dto.getName ()))
			this.setName (dto.getName ());

		if (!StringUtils.isEmpty (dto.getJob ()))
			this.setJob (dto.getJob ());

		if (dto.getBirthday () != null)
			this.setBirthday (new Birthday (dto.getBirthday ()));

		if (!StringUtils.isEmpty (dto.getAddress ()))
			this.setAddress (dto.getAddress ());

		if (!StringUtils.isEmpty (dto.getPhoneNumber ()))
			this.setPhoneNumber (dto.getPhoneNumber ());

		if (!StringUtils.isEmpty (dto.getHobby ()))
			this.setHobby (dto.getHobby ());
	}

	public Integer getAge ()
	{
		if (birthday == null)
			return null;

		return birthday.age ();
	}
}
