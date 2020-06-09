package farm.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.LoginBL;
import farm.request_model.login.LoginModel;
import farm.response_model.jwt.JwtResponse;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
//@CrossOrigin
public class LoginController {
	
	@Autowired
	private LoginBL loginBL;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "Login", notes="notes", nickname = "Login")
	public ResponseEntity<FarmResult<JwtResponse>> createFarmer(@RequestBody LoginModel authModel) throws Exception{
		
		try {
			FarmResult<JwtResponse> result = loginBL.login(authModel);
			
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);			
			}
			
			return new ResponseEntity<>(new FarmResult<>(result.getResult()), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
