package farm.request_model.animal;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;

@ApiModel
public class CreateCowModel extends CreateAnimalModel {
	
	public CreateCowModel() {
		super(ModelType.cowModel);
	}
}
