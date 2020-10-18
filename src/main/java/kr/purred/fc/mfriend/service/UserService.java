package kr.purred.fc.mfriend.service;

import kr.purred.fc.mfriend.domain.User;
import kr.purred.fc.mfriend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService
{
	@Autowired
	private UserRepository userRepository;

	/**
	 * @return 전체 사용자 리스트
	 */
	public List<User> getAllUsers ()
	{
		return userRepository.findAll ();
	}
}
