package farm.request_model.update_animal;

import farm.enums.ModelType;
import farm.request_model.BaseAnimalModel;
import io.swagger.annotations.ApiModel;

@ApiModel
public abstract class UpdateAnimalModel extends BaseAnimalModel {

	public UpdateAnimalModel(ModelType type) {
		super(type);
	}
}
