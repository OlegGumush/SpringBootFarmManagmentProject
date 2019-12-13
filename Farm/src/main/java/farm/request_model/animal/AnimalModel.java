package farm.request_model.animal;

import farm.enums.ModelType;
import farm.request_model.BaseModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public abstract class AnimalModel extends BaseModel {

    @ApiModelProperty(position = 1, required = true, allowableValues = "range[1, 20]")
	public String AnimalName;
    
    @ApiModelProperty(position = 2, required = true)
    public Long GroupId;

	public AnimalModel(ModelType type) {
		super(type);
	}
}
