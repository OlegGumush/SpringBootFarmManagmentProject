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
import farm.request_model.auth.AuthModel;
import farm.response_model.JwtResponse;
import farm.result.FarmResult;
import farm.utils.JwtUtils;

@Service
@Transactional
public class AuthenticationBL {

	@Autowired
	private UserBL userBL;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtUtils jwtUtils;

	public FarmResult<JwtResponse> login(AuthModel authModel) {
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authModel.getUsername(), authModel.getPassword()));
			UserDetails userDetails = null;
			
			try {
				userDetails = userBL.loadUserByUsername(authModel.getUsername());			
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






