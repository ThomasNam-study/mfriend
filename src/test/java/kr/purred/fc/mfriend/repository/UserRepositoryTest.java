package kr.purred.fc.mfriend.repository;

import kr.purred.fc.mfriend.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
class UserRepositoryTest
{
	private final String boardTestTitle = "테스트";

	private final String email = "purred@gmail.com";

	@Autowired
	UserRepository userRepository;

	@BeforeEach
	void init ()
	{
		User user = userRepository.save (User.builder ().name ("havi").password ("test").email (email).createdDate (LocalDateTime.now ()).build ());
	}

	@Test
	public void userTest ()
	{
		User user = userRepository.findByEmail (email);

		assertThat(user.getName ()).isEqualTo ("havi");
		assertThat(user.getPassword ()).isEqualTo ("test");
		assertThat(user.getEmail ()).isEqualTo (email);
	}
}