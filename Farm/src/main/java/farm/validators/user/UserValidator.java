package farm.validators.user;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Strings;

import farm.bl.UserBL;
import farm.entity.user.User;
import farm.enums.ErrorType;
import farm.error.FarmError;
import farm.request_model.user.UserModel;

@Component
public class UserValidator implements IUserValidator {

	@Autowired
	private UserBL userBL;
	
	public static final int NAME_MAXIMUM_LENGTH = 20;
	public static final int USERNAME_MAXIMUM_LENGTH = 20;
	public static final int EMAIL_MAXIMUM_LENGTH = 50;
	public static final int PASSWORD_MAXIMUM_LENGTH = 50;
	public static final int PASSWORD_MINIMUM_LENGTH = 6;

	@Override
	public ArrayList<FarmError> validateCreate(UserModel model) {
		
		ArrayList<FarmError> errors = validateBase(model);
		
		if(!errors.isEmpty()) {
			
			return errors;
		}
		
		
		
		return errors;
	}


	@Override
	public ArrayList<FarmError> validateUpdate(UserModel model, User user) {
		// TODO Auto-generated method stub
		return null;
	}
	
	private ArrayList<FarmError> validateBase(UserModel model) {
		
		ArrayList<FarmError> errors = new ArrayList<>();

		if (model == null) {
			
			errors.add(new FarmError(ErrorType.UserModelIsEmpty, "GroupNumber"));
			
		} else if (Strings.isNullOrEmpty(model.Name)) {
			
			errors.add(new FarmError(ErrorType.UserNameCannotBeEmpty, "GroupNumber"));
			
		} else if (model.Name.length() > NAME_MAXIMUM_LENGTH) {
			
			errors.add(new FarmError(ErrorType.UserNameCannotBeBiggerThanThreshold, "GroupNumber"));

		} else if (Strings.isNullOrEmpty(model.Username)) {
			
			errors.add(new FarmError(ErrorType.UserUsernameCannotBeEmpty, "GroupNumber"));
			
		} else if (model.Username.length() > USERNAME_MAXIMUM_LENGTH) {
			
			errors.add(new FarmError(ErrorType.UserUsernameCannotBeBiggerThanThreshold, "GroupNumber"));

		} else if (Strings.isNullOrEmpty(model.Email)) {
			
			errors.add(new FarmError(ErrorType.UserEmailCannotBeEmpty, "GroupNumber"));
			
		} else if (model.Email.length() > EMAIL_MAXIMUM_LENGTH) {
			
			errors.add(new FarmError(ErrorType.UserEmailCannotBeBiggerThanThreshold, "GroupNumber"));

		} else if (Strings.isNullOrEmpty(model.Password)) {
			
			errors.add(new FarmError(ErrorType.UserPasswordCannotBeEmpty, "GroupNumber"));
			
		} else if (model.Password.length() < PASSWORD_MINIMUM_LENGTH || model.Password.length() > PASSWORD_MAXIMUM_LENGTH) {
			
			errors.add(new FarmError(ErrorType.UserPasswordCannotBeBiggerOrSmallerThanThreshold, "GroupNumber"));
		}
		
		return errors;
	}
}










