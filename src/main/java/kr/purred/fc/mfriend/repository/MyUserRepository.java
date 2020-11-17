package kr.purred.fc.mfriend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.purred.fc.mfriend.domain.MyUser;

public interface MyUserRepository extends JpaRepository<MyUser, Long>
{
}
