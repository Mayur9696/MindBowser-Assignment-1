package com.user.management.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.user.management.model.UserMetaModel;
import com.user.management.reposetory.UserReposetory;
import com.user.management.system.ResponseMessage;

@RestController
public class AuthController {

	@Autowired
	UserReposetory userRepo;

	@PostMapping("/login")
	public @ResponseBody Object auth(HttpServletRequest request, HttpSession session, @RequestParam String email,
			@RequestParam String password) throws Exception {

		try {

			UserMetaModel usermodel = userRepo.findByemail(email);
			if (usermodel != null) {
				if (usermodel.getEmail().equals(email) && usermodel.getPassword().equals(password)) {
					session = request.getSession();
					session.setAttribute("email", usermodel.getEmail());

					session.setAttribute("name", usermodel.getName());

					session.setAttribute("password", usermodel.getPassword());
					return ResponseMessage.message("User Loged in successfully", 200, true);
				} else {
					return ResponseMessage.message("Email or password not match", 404, false);
				}

			} else {
				return ResponseMessage.message("Account Not Found", 404, false);
			}
		} catch (Exception e) {
			return ResponseMessage.message("Some error has occured", 500, false);
		}
		
	}
	
	@PostMapping("/logout")
	public Object logout(HttpSession session) throws Exception {
		if (session.getAttributeNames() != null) {
			session.invalidate();
			
			return ResponseMessage.message("User Loged out successfully", 200, true);
		}
		return ResponseMessage.message("User Loged out successfully", 200, true);
	}

}
