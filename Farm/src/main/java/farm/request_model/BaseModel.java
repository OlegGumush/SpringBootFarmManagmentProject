package farm.model;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseModel {
	
    @ApiModelProperty(hidden = true)
	public ModelType type;
	
	public BaseModel(ModelType type) {
		this.type = type;
	}
}
