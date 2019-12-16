package farm.validators.user;

import java.util.ArrayList;

import farm.entity.user.User;
import farm.error.FarmError;
import farm.request_model.user.UserModel;
import farm.validators.IValidator;

public interface IUserValidator extends IValidator {

	ArrayList<FarmError> validateCreate(UserModel model);
	
	ArrayList<FarmError> validateUpdate(UserModel model, User user); 
}
