package farm.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.UserBL;

@RestController
@CrossOrigin
@Transactional
public class UserController {

	@Autowired
	private UserBL userBL;
	
	
	public UserController() {
	}
}
