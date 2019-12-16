package farm.request_model.animal;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;

@ApiModel
public abstract class UpdateAnimalModel extends BaseAnimalModel {

	public UpdateAnimalModel(ModelType type) {
		super(type);
	}
}
