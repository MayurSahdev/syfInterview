package com.syf.interview.imageresource.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.syf.interview.imageresource.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
