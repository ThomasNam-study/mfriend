package kr.purred.fc.mfriend.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Data
@Entity
public class Block
{
	@Id
	@GeneratedValue
	private Long id;

	private boolean block;

	private String name;

	private String reason;

	private LocalDate startDate;

	private LocalDate endDate;
}
