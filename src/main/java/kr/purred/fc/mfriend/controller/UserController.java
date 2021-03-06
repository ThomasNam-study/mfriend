package kr.purred.fc.mfriend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import kr.purred.fc.mfriend.domain.User;
import kr.purred.fc.mfriend.service.UserService;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/user")
@RestController
@Slf4j
public class UserController
{
	@Autowired
	UserService userService;

	@GetMapping
	public List<User> getAll ()
	{
		return userService.getAllUsers ();
	}

	@GetMapping("{no}")
	public User getOne (@PathVariable ("no") Long no)
	{
		return userService.getOne (no);
	}
}
