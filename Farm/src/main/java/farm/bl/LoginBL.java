package farm.bl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import farm.enums.ErrorType;
import farm.error.FarmError;
import farm.request_model.login.LoginModel;
import farm.response_model.jwt.JwtResponse;
import farm.result.FarmResult;
import farm.utils.JwtUtils;

@Service
@Transactional
public class LoginBL {

	@Autowired
	private UserBL userBL;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;

	public FarmResult<JwtResponse> login(LoginModel authModel) {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.Username, authModel.Password));
			UserDetails userDetails = null;
			
			try {
				userDetails = userBL.loadUserByUsername(authModel.Username);			
			} catch (UsernameNotFoundException e) {
				return new FarmResult<>(new FarmError(ErrorType.UserNotExists));
			}			

			String jwt = jwtUtils.generateToken(userDetails);
			return new FarmResult<JwtResponse>(new JwtResponse(jwt));
		
		} catch (Exception e) {
			return new FarmResult<>(new FarmError(ErrorType.BadCredentials));
		}
	}
}






