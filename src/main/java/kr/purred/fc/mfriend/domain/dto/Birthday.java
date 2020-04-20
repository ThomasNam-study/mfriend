package kr.purred.fc.mfriend.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Birthday
{
	private Integer year;

	/*@Min (1)
	@Max (12)*/
	private Integer month;

	/*@Min (1)
	@Max (31)*/
	private Integer day;

	public Birthday (LocalDate date)
	{
		this.year = date.getYear ();
		this.month = date.getMonthValue ();
		this.day = date.getDayOfMonth ();
	}

	public int age ()
	{
		return LocalDate.now ().getYear () - this.year + 1;
	}

	public boolean birthdayToday ()
	{
		return LocalDate.now ().equals (LocalDate.of (this.year, this.month, this.day));
	}

	public static Birthday of (LocalDate date)
	{
		return new Birthday (date);
	}
}
