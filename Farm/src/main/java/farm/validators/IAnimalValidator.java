package farm.validators;

import farm.entity.Animal;
import farm.model.AnimalModel;
import farm.result.FarmResult;

public interface IAnimalValidator extends IValidator {

	FarmResult validateCreate(AnimalModel model);
	
	FarmResult validateUpdate(AnimalModel model, Animal animal);
	
	FarmResult validateDelete(AnimalModel model, Animal animal);
}
