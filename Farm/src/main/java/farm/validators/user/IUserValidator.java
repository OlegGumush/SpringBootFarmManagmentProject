package farm.validators.user;

import java.util.ArrayList;

import farm.entity.user.User;
import farm.error.FarmError;
import farm.request_model.user.CreateUserModel;
import farm.request_model.user.UpdateUserModel;
import farm.validators.IValidator;

public interface IUserValidator extends IValidator {

	ArrayList<FarmError> validateCreate(CreateUserModel model);
	
	ArrayList<FarmError> validateUpdate(UpdateUserModel model, User user); 
}
