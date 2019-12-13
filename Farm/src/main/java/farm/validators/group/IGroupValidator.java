package farm.validators.group;

import java.util.ArrayList;

import farm.entity.group.Group;
import farm.error.FarmError;
import farm.request_model.group.GroupModel;
import farm.validators.IValidator;

public interface IGroupValidator extends IValidator {

	ArrayList<FarmError> validateCreate(GroupModel groupModel);
	
	ArrayList<FarmError> validateUpdate(GroupModel groupModel, Group group);
}
