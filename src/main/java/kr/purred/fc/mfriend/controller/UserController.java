package kr.purred.fc.mfriend.controller;

import kr.purred.fc.mfriend.domain.User;
import kr.purred.fc.mfriend.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
