package farm.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.AuthenticationBL;
import farm.request_model.auth.AuthModel;
import farm.response_model.JwtResponse;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
//@CrossOrigin
public class AuthenticationController {
	
	@Autowired
	private AuthenticationBL authenticationBL;
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ApiOperation(value = "Login", notes="notes", nickname = "Login")
	public ResponseEntity<FarmResult<JwtResponse>> createFarmer(@RequestBody AuthModel authModel) throws Exception{
		
		try {
			FarmResult<JwtResponse> result = authenticationBL.login(authModel);
			
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);			
			}
			
			return new ResponseEntity<>(new FarmResult<>(result.getResult()), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
