package kr.purred.fc.mfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import kr.purred.fc.mfriend.domain.User;
import kr.purred.fc.mfriend.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

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

	public User getOne(Long no)
	{
		return userRepository.findById (no).get ();
	}
}
