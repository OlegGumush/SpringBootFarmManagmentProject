package farm.model;

import farm.enums.ModelType;

public abstract class AnimalModel extends BaseModel {

	public String AnimalName;

	public AnimalModel(ModelType type) {
		super(type);
	}
}
