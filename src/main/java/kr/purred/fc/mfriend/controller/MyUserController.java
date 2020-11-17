package kr.purred.fc.mfriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import kr.purred.fc.mfriend.domain.MyUser;
import kr.purred.fc.mfriend.service.MyUserService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/myuser")
@RestController
@Slf4j
public class MyUserController
{
	@Autowired
	MyUserService userService;

	@GetMapping
	public List<MyUser> getAll ()
	{
		return userService.getAllUsers ();
	}

	@GetMapping("{no}")
	public MyUser getOne (@PathVariable("no") Long no)
	{
		return userService.getOne (no);
	}
}
