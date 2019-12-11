package farm.model;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;

@ApiModel
public class CowModel extends AnimalModel {
	
	public CowModel() {
		super(ModelType.cowModel);
	}
}
