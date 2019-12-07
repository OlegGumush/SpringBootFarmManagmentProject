package farm.model;

import farm.enums.ModelType;
import io.swagger.annotations.ApiModel;

@ApiModel
public class BullModel extends AnimalModel {

	public BullModel() {
		super(ModelType.bullModel);
	}
}
