package farm.request_model.animal;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;

@ApiModel
public class UpdateBullModel extends UpdateAnimalModel {

	public UpdateBullModel() {
		super(ModelType.bullModel);
	}
}
