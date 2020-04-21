package kr.purred.fc.mfriend.repository;

import kr.purred.fc.mfriend.domain.Board;
import kr.purred.fc.mfriend.domain.User;
import kr.purred.fc.mfriend.enums.BoardType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class BoardRepositoryTest
{
	private final String boardTestTitle = "테스트";

	private final String email = "purred@gmail.com";

	@Autowired
	UserRepository userRepository;

	@Autowired
	BoardRepository boardRepository;

	@BeforeEach
	void init ()
	{
		User user = userRepository.save (User.builder ().name ("havi").password ("test").email (email).createdDate (LocalDateTime.now ()).build ());

		boardRepository.save (Board.builder ()
				.title (boardTestTitle)
				.subTitle ("서브 타이틀")
				.content ("내용")
				.boardType (BoardType.free)
				.createdDate (LocalDateTime.now ())
				.updatedDate (LocalDateTime.now ())
				.user (user)
				.build ());
	}

	@Test
	public void getTest ()
	{
		User user = userRepository.findByEmail (email);

		assertThat(user.getName ()).isEqualTo ("havi");
		assertThat(user.getPassword ()).isEqualTo ("test");
		assertThat(user.getEmail ()).isEqualTo (email);

		Board board = boardRepository.findByUser (user);

		assertThat(board.getTitle ()).isEqualTo (boardTestTitle);
		assertThat(board.getSubTitle ()).isEqualTo ("서브 타이틀");
		assertThat(board.getContent ()).isEqualTo ("내용");
		assertThat(board.getBoardType ()).isEqualTo (BoardType.free);
	}
}