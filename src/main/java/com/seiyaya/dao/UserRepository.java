package com.seiyaya.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.seiyaya.bean.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
