package com.example.springsecurity.repo;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springsecurity.model.UserModel;

@Repository
public interface UserRepo  extends JpaRepository<UserModel,Long>{
	UserModel findByUsername(String username);
}
