package farm.model.animal;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;

@ApiModel
public class CowModel extends AnimalModel {
	
	public CowModel() {
		super(ModelType.cowModel);
	}
}
