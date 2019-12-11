package farm.model.group;

import farm.enums.ModelType;
import farm.model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class GroupModel extends BaseModel{

    @ApiModelProperty(position = 1, required = true, allowableValues = "range[1, 20]")
	public String GroupName;
    
    @ApiModelProperty(position = 2, required = true)
	public Long GroupNumber;
    
	public GroupModel() {
		super(ModelType.groupModel);
	}
}
