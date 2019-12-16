package farm.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import farm.entity.user.User;
import farm.enums.RoleType;
import farm.repository.UserRepository;
import farm.request_model.user.UserModel;
import farm.result.FarmResult;
import farm.security.FarmUserDetails;

@Service
public class UserBL implements UserDetailsService {

	@Autowired 
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findUserByName(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("User not found by name: " + username);
		}
		
		return new FarmUserDetails(user);
	}	
	
	public FarmResult<User> createUser(UserModel userModel, RoleType role) {
		
		User user = new User();
		user.setEmail(userModel.Email);
		user.setPassword(passwordEncoder.encode(userModel.Password));
		user.setName(userModel.Name);
		user.setUsername(userModel.Username);
		user.setRole(role);
		
		userRepository.insert(user);
		return new FarmResult<>(user);
	}
}
