package farm.bl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import farm.entity.user.User;
import farm.enums.CacheType;
import farm.enums.EntityType;
import farm.enums.RoleType;
import farm.error.FarmError;
import farm.repository.UserRepository;
import farm.request_model.user.CreateUserModel;
import farm.result.FarmResult;
import farm.security.FarmUserDetails;
import farm.validators.ValidatorFactory;
import farm.validators.user.IUserValidator;

@Service
public class UserBL implements UserDetailsService {

	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	protected ValidatorFactory validatorFactory;	
	
	@Override
	@Cacheable(value = CacheType.USER_CACHE, key = "#username" )
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findUserByName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found by name: " + username);
		}
		
		return new FarmUserDetails(user);
	}	
	
	public FarmResult<User> createUser(CreateUserModel userModel, RoleType role) {
		
		IUserValidator groupValidator = (IUserValidator) validatorFactory.getValidator(EntityType.User);
		ArrayList<FarmError> errors = groupValidator.validateCreate(userModel);
		
		if(!errors.isEmpty()) {
			return new FarmResult<>(errors);
		}
		
		User user = new User();
		user.setEmail(userModel.Email);
		user.setPassword(passwordEncoder.encode(userModel.Password));
		user.setName(userModel.Name);
		user.setUsername(userModel.Username);
		user.setRole(role);
		
		userRepository.insert(user);
		return new FarmResult<>(user);
	}
	
	public boolean isUserExistsByUsernameExceptId(String username, long id) {
		
		return userRepository.isUserExistsByUsernameExceptId(username, id);
	}
	
	public boolean isUserExistsByUsername(String username) {
		
		return userRepository.isUserExistsByUsernameExceptId(username, 0);
	}
	
	public boolean isUserExistsByEmailExceptId(String email, long id) {
		
		return userRepository.isUserExistsByEmailExceptId(email, id);
	}
	
	public boolean isUserExistsByEmail(String email) {
		
		return userRepository.isUserExistsByEmailExceptId(email, 0);
	}
}
