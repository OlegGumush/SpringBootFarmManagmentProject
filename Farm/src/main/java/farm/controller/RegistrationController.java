package farm.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.UserBL;
import farm.entity.user.User;
import farm.enums.RoleType;
import farm.request_model.user.UserModel;
import farm.response_model.user.UserResponseModel;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
@CrossOrigin
public class RegistrationController {
	
	@Autowired
	private UserBL userBL;
	
	@RequestMapping(value = "/registration/admin", method = RequestMethod.POST)
	@ApiOperation(value = "Create admin", notes="notes", nickname = "createAdmin")
    @PreAuthorize("hasRole('ROLE_SYSTEM')")
	public ResponseEntity<FarmResult<User>> createAdmin(@RequestBody UserModel userModel) {
		
		try {
			FarmResult<User> result = createUser(userModel, RoleType.ROLE_ADMIN); 
			 
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);
			}
	
			return new ResponseEntity<>(new FarmResult<>(result.getResult()), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/registration/farmer", method = RequestMethod.POST)
	@ApiOperation(value = "Create farmer", notes="notes", nickname = "CreateFarmer")
    @PreAuthorize("hasRole('ROLE_SYSTEM') or hasRole('ROLE_ADMIN')")
	public ResponseEntity<FarmResult<UserResponseModel>> createFarmer(@RequestBody UserModel userModel) {
		
		try {
			FarmResult<User> result = createUser(userModel, RoleType.ROLE_FARMER); 
			 
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);
			}
	
			return new ResponseEntity<>(new FarmResult<>(new UserResponseModel(result.getResult())), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	public FarmResult<User> createUser(UserModel userModel, RoleType role) {
		
		return userBL.createUser(userModel, role);
	}
}
