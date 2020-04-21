package kr.purred.fc.mfriend.repository;

import kr.purred.fc.mfriend.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long>
{
	User findByEmail (String email);
}
