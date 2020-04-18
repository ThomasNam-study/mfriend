package kr.purred.fc.mfriend.domain;

import kr.purred.fc.mfriend.domain.dto.Birthday;
import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class Person
{
	@Id
	@GeneratedValue
	private Long id;

	@NonNull
	private String name;

	@NonNull
	private int age;

	private String hobby;

	@NonNull
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
}
