package farm.validators.group;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import farm.bl.GroupBL;
import farm.entity.group.Group;
import farm.enums.ErrorType;
import farm.model.group.GroupModel;
import farm.result.FarmResult;

@Component
public class GroupValidator implements IGroupValidator {

	@Autowired
	private GroupBL groupBL;
	
	public static final int GROUP_NAME_MAXIMUM_LENGTH = 20;
	
	@Override
	public FarmResult validateCreate(GroupModel groupModel) {
		
		FarmResult result = validateBase(groupModel);
		
		if(!result.isSucceeded()) {
			
			return result;
		}
		
		if (groupBL.isGroupExistsByName(groupModel.GroupName)) {
			
			return new FarmResult(ErrorType.GroupNameAlreadyExists, "GroupNumber");
		}
		
		if (groupBL.isGroupExistsByNumber(groupModel.GroupNumber)) {
			
			return new FarmResult(ErrorType.GroupNumberAlreadyExists, "GroupNumber");
		}
		
		return new FarmResult();
	}

	@Override
	public FarmResult validateUpdate(GroupModel model, Group animal) {
		
		return new FarmResult();
	}

	@Override
	public FarmResult validateDelete(GroupModel model, Group animal) {
		
		return new FarmResult();
	}

	private FarmResult validateBase(GroupModel groupModel) {
		
		if(groupModel.GroupNumber == null) {		
			return new FarmResult(ErrorType.GroupNumberCannotBeEmpty, "GroupNumber");
		}
		
		if(Strings.isEmpty(groupModel.GroupName)) {
			
			return new FarmResult(ErrorType.GroupNameCannotBeEmpty, "GroupName");
		}
		
		if (groupModel.GroupName.length() > GROUP_NAME_MAXIMUM_LENGTH) {
			return new FarmResult(ErrorType.GroupNameCannotBeBiggerThanThreshold, "GroupName");
		}
		
		if (groupModel.GroupNumber == 0) {
			return new FarmResult(ErrorType.GroupNumberCannotBeZeroItRelatedToDefaultGroup, "GroupNumber");
		}
		
		if (groupModel.GroupNumber < 0) {
			return new FarmResult(ErrorType.GroupNumberCannotBeNegative, "GroupNumber");
		}
		
		return new FarmResult();
	}
}
