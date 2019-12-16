package farm.response_model.user;

import farm.entity.user.User;
import farm.response_model.BaseResponseModel;

public class UserResponseModel extends BaseResponseModel {

	public String Name;
	
	public String Email;
	
	public String Username;
    
	public UserResponseModel(User user) {
		
		super(user.getId(), user.getInsertedDateTime());
		this.Name = user.getName();
		this.Username = user.getUsername();
		this.Email = user.getEmail();
	}
}
