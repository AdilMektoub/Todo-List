package com.mode.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mode.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

}
