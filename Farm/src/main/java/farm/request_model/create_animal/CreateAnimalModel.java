package farm.request_model.create_animal;

import farm.enums.ModelType;
import farm.request_model.BaseAnimalModel;
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
