package kr.purred.fc.mfriend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import kr.purred.fc.mfriend.domain.MyUser;
import kr.purred.fc.mfriend.repository.MyUserRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MyUserService
{
	@Autowired
	private MyUserRepository myUserRepository;

	/**
	 * @return 전체 사용자 리스트
	 */
	public List<MyUser> getAllUsers ()
	{
		return myUserRepository.findAll ();
	}

	public MyUser getOne(Long no)
	{
		return myUserRepository.findById (no).get ();
	}
}
