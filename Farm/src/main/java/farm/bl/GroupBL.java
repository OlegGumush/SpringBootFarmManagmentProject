package farm.bl;

import java.util.ArrayList;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farm.entity.group.Group;
import farm.enums.EntityType;
import farm.error.FarmError;
import farm.repository.GroupRepository;
import farm.request_model.group.GroupModel;
import farm.result.FarmResult;
import farm.validators.ValidatorFactory;
import farm.validators.group.IGroupValidator;

@Service
@Transactional(value = TxType.REQUIRED)
public class GroupBL {

	@Autowired
	protected GroupRepository groupRepository;
	
	@Autowired
	protected ValidatorFactory validatorFactory;	
	
	public FarmResult<Group> createGroup(GroupModel groupModel) {
		
		IGroupValidator groupValidator = (IGroupValidator) validatorFactory.getValidator(EntityType.Group);
		ArrayList<FarmError> errors = groupValidator.validateCreate(groupModel);
		
		if(!errors.isEmpty()) {
			return new FarmResult<>(errors);
		}
		
		Group group = new Group();
		
		setCreateCommonFields(groupModel, group);

		groupRepository.insert(group);
		
		return new FarmResult<>(group);
	}

	public FarmResult<Group> updateGroup(GroupModel groupModel, long groupId) {
				
		FarmResult<Group> result = getGroupById(groupId);
		Group group = (Group) result.getResult();
		
		IGroupValidator groupValidator = (IGroupValidator) validatorFactory.getValidator(EntityType.Group);
		ArrayList<FarmError> errors = groupValidator.validateUpdate(groupModel, group);

		if(!errors.isEmpty()) {
			return result;
		}
		
		setCreateCommonFields(groupModel, group);

		groupRepository.update(group);
		
		return new FarmResult<>(group);
	}
	
	public FarmResult<Group> getGroupById(long groupId) {
				
		Group group =  groupRepository.findById(groupId);		
		return new FarmResult<>(group);
	}

	public boolean isGroupExistsByNameExceptId(String groupName, long groupId) {
		
		return groupRepository.isGroupExistsByNameExceptId(groupName, groupId);
	}
	
	public boolean isGroupExistsByName(String groupName) {
		
		return groupRepository.isGroupExistsByNameExceptId(groupName, 0);
	}
	
	public boolean isGroupExistsByNumberExceptId(long animalNumber, long groupId) {
		
		return groupRepository.isGroupExistsByNumberExceptId(animalNumber, groupId);
	}
	
	public boolean isGroupExistsByNumber(long animalNumber) {
		
		return groupRepository.isGroupExistsByNumberExceptId(animalNumber, 0);
	}
	
	public Group findGroupById(long groupId) {
		
		return groupRepository.findById(groupId);
	}
	
	public boolean isGroupExistById(long groupId) {
		return groupRepository.isGroupExistById(groupId);
	}
	
	private void setCreateCommonFields(GroupModel groupModel, Group group) {

		group.setGroupName(groupModel.GroupName);
		group.setGroupNumber(groupModel.GroupNumber);
	}
}
