package farm.model;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseModel {
	
    @ApiModelProperty(hidden = true)
	protected ModelType type;
	
	public BaseModel(ModelType type) {
		this.type = type;
	}

	public ModelType getType() {
		return type;
	}

	public void setType(ModelType type) {
		this.type = type;
	}
}
