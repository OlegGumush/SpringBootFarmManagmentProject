package farm.validators.group;

import farm.entity.group.Group;
import farm.model.group.GroupModel;
import farm.result.FarmResult;
import farm.validators.IValidator;

public interface IGroupValidator extends IValidator {

	FarmResult validateCreate(GroupModel groupModel);
	
	FarmResult validateUpdate(GroupModel groupModel, Group group);
	
	FarmResult validateDelete(GroupModel groupModel, Group group);
}
