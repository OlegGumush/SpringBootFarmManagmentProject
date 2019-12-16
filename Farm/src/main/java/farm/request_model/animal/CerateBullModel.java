package farm.request_model.animal;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;

@ApiModel
public class CerateBullModel extends CreateAnimalModel {

	public CerateBullModel() {
		super(ModelType.bullModel);
	}
}
