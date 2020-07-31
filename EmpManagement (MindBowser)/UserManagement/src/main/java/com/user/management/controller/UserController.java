package com.user.management.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.model.UserMetaModel;
import com.user.management.model.UserModel;
import com.user.management.reposetory.UserReposetory;
import com.user.management.system.LoginController;
import com.user.management.system.ResponseMessage;

@RestController
public class UserController {

	@Autowired
	UserReposetory userRepo;

	@PostMapping("/add")
	public Object addUser(@RequestBody UserModel userModel) {

		try {
			UserMetaModel userMetaModel = new UserMetaModel();
			userMetaModel.setName(userModel.getName());
			userMetaModel.setEmail(userModel.getEmail());
			userMetaModel.setContact(userModel.getContact());
			if (userModel.getPassword().equals(userModel.getConfirmPassword())) {
				userMetaModel.setPassword(userModel.getPassword());

			} else {
				return ResponseMessage.message("Password and ConfirmPassword did not Match", 500, false);

			}
			userRepo.save(userMetaModel);
			return ResponseMessage.message("User Added Successfully", 200, true);
		} catch (Exception e) {
			return ResponseMessage.message("Some error has occured", 500, false);
		}

	}

	@PostMapping("/update")
	public Object updateUser(@RequestBody UserModel userModel, HttpSession session) {

		try {
			if (!LoginController.userValidate(session)) {

				return ResponseMessage.message("user is not login", 600, false);
			}
			UserMetaModel userMetaModel = userRepo.findByemail(userModel.getEmail());
			userMetaModel.setName(userModel.getName());
			userMetaModel.setEmail(userModel.getEmail());
			userMetaModel.setContact(userModel.getContact());
			if (userModel.getPassword().equals(userModel.getConfirmPassword())) {
				userMetaModel.setPassword(userModel.getPassword());

			} else {
				return ResponseMessage.message("Password and ConfirmPassword did not Match", 500, false);

			}
			userRepo.save(userMetaModel);
			return ResponseMessage.message("User Updated Successfully Successfully", 200, true);
		} catch (Exception e) {
			return ResponseMessage.message("Some error has occured", 500, false);
		}

	}

	@PostMapping("/delete")
	public Object deleteUser(@RequestParam String email, HttpSession session) {

		try {
			if (!LoginController.userValidate(session)) {

				return ResponseMessage.message("user is not login", 600, false);
			}
			UserMetaModel userMetaModel = userRepo.findByemail(email);
			userRepo.delete(userMetaModel);
			return ResponseMessage.message("User Deleted Successfully", 200, true);
		} catch (Exception e) {
			return ResponseMessage.message("Some error has occured", 500, false);
		}

	}

	@PostMapping("/getuser")
	public Object getUser(@RequestParam String email, HttpSession session) {
		try {
			if (!LoginController.userValidate(session)) {

				return ResponseMessage.message("user is not login", 600, false);
			}
			UserMetaModel userMetaModel = userRepo.findByemail(email);

			return ResponseMessage.message("User Data", 200, true, userMetaModel);
		} catch (Exception e) {
			return ResponseMessage.message("Some error has occured", 500, false);
		}

	}

	@PostMapping("/getalluser")
	public Object getAllUser(HttpSession session) {
		try {
			if (!LoginController.userValidate(session)) {

				return ResponseMessage.message("user is not login", 600, false);
			}
			List<UserMetaModel> userMetaModel = userRepo.findAll();

			return ResponseMessage.message("All User data", 200, true, userMetaModel);
		} catch (Exception e) {
			return ResponseMessage.message("Some error has occured", 500, false);
		}

	}
}
