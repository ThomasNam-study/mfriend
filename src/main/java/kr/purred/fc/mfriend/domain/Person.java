package kr.purred.fc.mfriend.domain;

import kr.purred.fc.mfriend.domain.dto.Birthday;
import kr.purred.fc.mfriend.dto.PersonDto;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NonNull
	@NotEmpty
	@Column(nullable = false)
	private String name;

	@NonNull
	@Min(1)
	private int age;

	private String hobby;

	@NonNull
	@NotEmpty
	@Column(nullable = false)
	private String bloodType;

	private String address;

	// private LocalDate birthday;
	@Embedded
	@Valid
	private Birthday birthday;

	private String job;

	@ToString.Exclude
	private String phoneNumber;

	// @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE})
	// @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.EAGER, optional = false)
	// @OneToOne(cascade = {CascadeType.ALL}, orphanRemoval = true, fetch = FetchType.LAZY)
	@OneToOne(cascade = CascadeType.ALL)
	@ToString.Exclude
	private Block block;

	public void set (PersonDto dto)
	{
		if (dto.getAge () > 0)
			this.setAge (dto.getAge ());

		if (!StringUtils.isEmpty (dto.getName ()))
			this.setName (dto.getName ());

		if (!StringUtils.isEmpty (dto.getJob ()))
			this.setJob (dto.getJob ());

		if (dto.getBirthday () != null)
			this.setBirthday (new Birthday (dto.getBirthday ()));

		if (!StringUtils.isEmpty (dto.getAddress ()))
			this.setAddress (dto.getAddress ());

		if (!StringUtils.isEmpty (dto.getBloodType ()))
			this.setBloodType (dto.getBloodType ());

		if (!StringUtils.isEmpty (dto.getPhoneNumber ()))
			this.setPhoneNumber (dto.getPhoneNumber ());

		if (!StringUtils.isEmpty (dto.getHobby ()))
			this.setHobby (dto.getHobby ());
	}
}
