package kr.purred.fc.mfriend;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

import kr.purred.fc.mfriend.domain.Board;
import kr.purred.fc.mfriend.domain.MyUser;
import kr.purred.fc.mfriend.domain.User;
import kr.purred.fc.mfriend.enums.BoardType;
import kr.purred.fc.mfriend.repository.BoardRepository;
import kr.purred.fc.mfriend.repository.MyUserRepository;
import kr.purred.fc.mfriend.repository.UserRepository;

@SpringBootApplication
public class MfriendApplication
{

	public static void main (String[] args)
	{
		SpringApplication.run (MfriendApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner (UserRepository userRepository, MyUserRepository myUserRepository, BoardRepository boardRepository)
	{
		return (args) -> {

			for (int i = 0; i < 10; i++)
			{
				userRepository.save (User.builder ().name ("purred" + i).password ("test" + i).email ("purred" + i + "@gmail.com").createdDate (LocalDateTime.now ()).build ());
				myUserRepository.save (MyUser.builder ().userName ("mymy" + i).password ("test" + i).userEmail ("purred" + i + "@gmail.com").createdDate (LocalDateTime.now ()).build ());
			}


			User user = userRepository.save (User.builder ().name ("havi").password ("test").email ("purred@gmail.com").createdDate (LocalDateTime.now ()).build ());

			IntStream.rangeClosed (1, 200).forEach ((index) -> {
				boardRepository.save (Board.builder ()
						.title ("게시글" + index)
						.subTitle ("순서" + index)
						.content ("내용")
						.boardType (BoardType.free)
						.createdDate (LocalDateTime.now ())
						.updatedDate (LocalDateTime.now ())
						.user (user)
						.build ());
			});
		};
	}
}
