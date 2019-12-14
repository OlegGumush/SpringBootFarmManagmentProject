package farm.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.UserBL;
import farm.request_model.AuthModel;
import farm.response_model.JwtResponse;
import farm.result.FarmResult;
import farm.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
public class AuthenticationController {

	@Autowired
	private UserBL userBL;
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@RequestMapping(value = "/authentication", method = RequestMethod.POST)
	@ApiOperation(value = "authentication", notes="notes", nickname = "authentication")
	public ResponseEntity<FarmResult<JwtResponse>> createFarmer(@RequestBody AuthModel authModel) throws Exception{
		
		authManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getUsername(), authModel.getPassword()));
		
		UserDetails userDetails = userBL.loadUserByUsername(authModel.getUsername());
		
//		if (!encoder.matches(authModel.getPassword(), userDetails.getPassword())) {
//			return null;
//		}
		
		String jwt = jwtUtils.generateToken(userDetails);
		
		return new ResponseEntity<>(new FarmResult<>(new JwtResponse(jwt)), HttpStatus.CREATED);
	}
}
