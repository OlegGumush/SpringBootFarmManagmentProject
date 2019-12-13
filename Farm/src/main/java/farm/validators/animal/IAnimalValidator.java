package farm.validators.animal;

import java.util.ArrayList;

import farm.entity.animal.Animal;
import farm.error.FarmError;
import farm.request_model.animal.AnimalModel;
import farm.validators.IValidator;

public interface IAnimalValidator extends IValidator {

	ArrayList<FarmError> validateCreate(AnimalModel model);
	
	ArrayList<FarmError> validateUpdate(AnimalModel model, Animal animal);
}
