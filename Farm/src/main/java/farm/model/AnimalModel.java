package farm.model;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public abstract class AnimalModel extends BaseModel {

    @ApiModelProperty(position = 1, required = true, allowableValues = "range[1, 20]")
	public String AnimalName;

	public AnimalModel(ModelType type) {
		super(type);
	}
}
