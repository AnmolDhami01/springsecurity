package com.example.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springsecurity.model.JwtModel;


public interface JwtModelRepo extends JpaRepository<JwtModel, Long> {
	JwtModel findByUserId(Long uid);

}
