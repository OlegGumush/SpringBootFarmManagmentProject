package farm.validators.animal;

import java.util.ArrayList;

import farm.entity.animal.Animal;
import farm.error.FarmError;
import farm.request_model.create_animal.CreateAnimalModel;
import farm.request_model.update_animal.UpdateAnimalModel;
import farm.validators.IValidator;

public interface IAnimalValidator extends IValidator {

	ArrayList<FarmError> validateCreate(CreateAnimalModel model);
	
	ArrayList<FarmError> validateUpdate(UpdateAnimalModel model, Animal animal); 
}
