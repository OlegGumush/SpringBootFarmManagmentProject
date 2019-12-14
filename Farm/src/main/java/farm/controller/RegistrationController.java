package farm.controller;

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
import farm.request_model.BaseUserModel;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin
public class RegistrationController {
	
	@Autowired
	private UserBL userBL;

	
	@RequestMapping(value = "/registration/admin", method = RequestMethod.POST)
	@ApiOperation(value = "Create admin", notes="notes", nickname = "createAdmin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<FarmResult<User>> createAdmin(@RequestBody BaseUserModel userModel) {
		
		try {
			FarmResult<User> result = userBL.createAdmin(userModel);
			 
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
	public ResponseEntity<FarmResult<User>> createFarmer(@RequestBody BaseUserModel userModel) {
		
		try {
			FarmResult<User> result = userBL.createFarmer(userModel);
			 
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);
			}
	
			return new ResponseEntity<>(new FarmResult<>(result.getResult()), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
}
