package farm.response_model.group;

import farm.entity.group.Group;
import farm.response_model.BaseResponseModel;

public class GroupResponseModel extends BaseResponseModel {

	public String GroupName;
	
	public Long GroupNumber;
    
	public GroupResponseModel(Group group) {
		
		super(group.getId(), group.getInsertedDateTime());
		this.GroupName = group.getGroupName();
		this.GroupNumber = group.getGroupNumber();
	}
}
