package farm.validators.group;

import java.util.ArrayList;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import farm.bl.GroupBL;
import farm.entity.group.Group;
import farm.enums.ErrorType;
import farm.error.FarmError;
import farm.request_model.group.GroupModel;

@Component
public class GroupValidator implements IGroupValidator {

	@Autowired
	private GroupBL groupBL;
	
	public static final int GROUP_NAME_MAXIMUM_LENGTH = 20;
	
	@Override
	public ArrayList<FarmError> validateCreate(GroupModel groupModel) {
		
		ArrayList<FarmError> errors = validateBase(groupModel);
		
		if(!errors.isEmpty()) {
			
			return errors;
		}
		
		if (groupBL.isGroupExistsByName(groupModel.GroupName)) {
			
			errors.add(new FarmError(ErrorType.GroupNameAlreadyExists, "GroupNumber"));
			
		} else if (groupBL.isGroupExistsByNumber(groupModel.GroupNumber)) {
			
			errors.add(new FarmError(ErrorType.GroupNumberAlreadyExists, "GroupNumber"));
		}
		
		return errors;
	}  

	@Override
	public ArrayList<FarmError> validateUpdate(GroupModel groupModel, Group group) {
		
		ArrayList<FarmError> errors = new ArrayList<>();

		if (group == null) {		
			
			errors.add(new FarmError(ErrorType.GroupNotNotExists, "Id"));
		}
		
		errors = validateBase(groupModel);
		
		if(!errors.isEmpty()) {
			
			return errors;
		}
		
		if (groupBL.isGroupExistsByNameExceptId(groupModel.GroupName, group.getId())) {
			
			errors.add(new FarmError(ErrorType.GroupNameAlreadyExists, "GroupName"));
			
		} else if (groupBL.isGroupExistsByNumberExceptId(groupModel.GroupNumber, group.getId())) {
			
			errors.add(new FarmError(ErrorType.GroupNumberAlreadyExists, "GroupNumber"));
		}
		
		return errors;
	}

	private ArrayList<FarmError> validateBase(GroupModel groupModel) {
		
		ArrayList<FarmError> errors = new ArrayList<>();
		
		if(groupModel.GroupNumber == null) {
			
			errors.add(new FarmError(ErrorType.GroupNumberCannotBeEmpty, "GroupNumber"));
			
		} else if(Strings.isEmpty(groupModel.GroupName)) {
			
			errors.add(new FarmError(ErrorType.GroupNameCannotBeEmpty, "GroupName"));
			
		} else if (groupModel.GroupName.length() > GROUP_NAME_MAXIMUM_LENGTH) {
			
			errors.add(new FarmError(ErrorType.GroupNameCannotBeBiggerThanThreshold, "GroupName"));
			
		} else if (groupModel.GroupNumber == 0) {
			
			errors.add(new FarmError(ErrorType.GroupNumberCannotBeZeroItRelatedToDefaultGroup, "GroupNumber"));
			
		} else if (groupModel.GroupNumber < 0) {
			
			errors.add(new FarmError(ErrorType.GroupNumberCannotBeNegative, "GroupNumber"));
		}
		
		return errors;
	}
}
