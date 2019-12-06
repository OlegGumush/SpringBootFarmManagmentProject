package farm.validators.animal;

import farm.entity.animal.Animal;
import farm.model.AnimalModel;
import farm.result.FarmResult;
import farm.validators.IValidator;

public interface IAnimalValidator extends IValidator {

	FarmResult validateCreate(AnimalModel model);
	
	FarmResult validateUpdate(AnimalModel model, Animal animal);
	
	FarmResult validateDelete(AnimalModel model, Animal animal);
}
