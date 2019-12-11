package farm.bl;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farm.entity.group.Group;
import farm.model.group.GroupModel;
import farm.repository.GroupRepository;
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
	
	public FarmResult createGroup(GroupModel groupModel) {
		
		IGroupValidator groupValidator = (IGroupValidator) validatorFactory.getValidator(groupModel);
		FarmResult result = groupValidator.validateCreate(groupModel);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		Group group = new Group();
		
		setCreateCommonFields(groupModel, group);

		groupRepository.insert(group);
		
		result.setResult(group.getId());
		return result;
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
