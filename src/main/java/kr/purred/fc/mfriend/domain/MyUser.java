package kr.purred.fc.mfriend.domain;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@Entity
@Table
@AllArgsConstructor
@Builder
@ToString
public class MyUser implements Serializable
{
	@Id
	@Column
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private Long no;

	@Column
	private String userName;

	@Column
	private String password;

	@Column
	private String userEmail;

	@Column
	private LocalDateTime createdDate;

	@Column
	private LocalDateTime updatedDate;
}
