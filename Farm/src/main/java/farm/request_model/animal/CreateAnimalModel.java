package farm.request_model.animal;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public abstract class CreateAnimalModel extends BaseAnimalModel {
    
    @ApiModelProperty(required = true)
    public Long GroupId;

	public CreateAnimalModel(ModelType type) {
		super(type);
	}
}
