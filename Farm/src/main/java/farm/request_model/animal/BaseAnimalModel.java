package farm.request_model.animal;

import farm.enums.ModelType;
import farm.request_model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public abstract class BaseAnimalModel extends BaseModel {

    @ApiModelProperty(required = true, allowableValues = "range[1, 20]")
	public String AnimalName;
    
	public BaseAnimalModel(ModelType type) {
		super(type);
	}
}
