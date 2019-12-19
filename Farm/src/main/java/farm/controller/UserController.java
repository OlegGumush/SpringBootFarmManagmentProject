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
import farm.request_model.user.CreateUserModel;
import farm.response_model.user.UserResponseModel;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
@CrossOrigin
public class UserController {
	
	@Autowired
	private UserBL userBL;
	
	@RequestMapping(value = "/user/admin", method = RequestMethod.POST)
	@ApiOperation(value = "Create admin", notes="notes", nickname = "createAdmin")
    @PreAuthorize(RoleType.SYSTEM)
	public ResponseEntity<FarmResult<UserResponseModel>> createAdmin(@RequestBody CreateUserModel userModel) {
		
		try {
			FarmResult<User> result = createUser(userModel, RoleType.ROLE_ADMIN); 
			 
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);
			}
	
			return new ResponseEntity<>(new FarmResult<>(new UserResponseModel(result.getResult())), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/user/farmer", method = RequestMethod.POST)
	@ApiOperation(value = "Create farmer", notes="notes", nickname = "CreateFarmer")
    @PreAuthorize(RoleType.SYSTEM + RoleType.OR + RoleType.ADMIN)
	public ResponseEntity<FarmResult<UserResponseModel>> createFarmer(@RequestBody CreateUserModel userModel) {
		
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
	
	public FarmResult<User> createUser(CreateUserModel userModel, RoleType role) {
		
		return userBL.createUser(userModel, role);
	}
}
