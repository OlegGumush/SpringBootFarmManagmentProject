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
		
		return new FarmResult<Group>(group);
	}

	public FarmResult<Group> updateGroup(GroupModel groupModel, long id) {
				
		FarmResult<Group> result = getGroupById(id);
		Group group = (Group) result.getResult();
		
		IGroupValidator groupValidator = (IGroupValidator) validatorFactory.getValidator(EntityType.Group);
		ArrayList<FarmError> errors = groupValidator.validateUpdate(groupModel, group);

		if(!errors.isEmpty()) {
			return result;
		}
		
		setCreateCommonFields(groupModel, group);

		groupRepository.update(group);
		
		return new FarmResult<Group>(group);
	}
	
	public FarmResult<Group> getGroupById(long id) {
				
		Group group =  groupRepository.findById(id);		
		return new FarmResult<Group>(group);
	}

	public boolean isGroupExistsByNameExceptId(String groupName, long id) {
		
		return groupRepository.isGroupExistsByNameExceptId(groupName, id);
	}
	
	public boolean isGroupExistsByName(String groupName) {
		
		return groupRepository.isGroupExistsByNameExceptId(groupName, 0);
	}
	
	public boolean isGroupExistsByNumberExceptId(long animalNumber, long id) {
		
		return groupRepository.isGroupExistsByNumberExceptId(animalNumber, id);
	}
	
	public boolean isGroupExistsByNumber(long animalNumber) {
		
		return groupRepository.isGroupExistsByNumberExceptId(animalNumber, 0);
	}
	
	public Group findGroupById(long id) {
		
		return groupRepository.findById(id);
	}
	
	public boolean isGroupExistById(long id) {
		return groupRepository.isGroupExistById(id);
	}
	
	private void setCreateCommonFields(GroupModel groupModel, Group group) {

		group.setGroupName(groupModel.GroupName);
		group.setGroupNumber(groupModel.GroupNumber);
	}

}
