package farm.request_model.update_animal;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;

@ApiModel
public class UpdateCowModel extends UpdateAnimalModel {
	
	public UpdateCowModel() {
		super(ModelType.cowModel);
	}
}
