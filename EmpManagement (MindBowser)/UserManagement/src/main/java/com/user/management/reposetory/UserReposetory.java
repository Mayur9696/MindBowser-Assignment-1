package com.user.management.reposetory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.management.model.UserMetaModel;

public interface UserReposetory extends JpaRepository<UserMetaModel, Integer> {
	
	UserMetaModel findByemail(String email);

}
