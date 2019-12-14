package farm.validators.animal;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import farm.entity.animal.Animal;
import farm.enums.AnimalSexType;
import farm.enums.ErrorType;
import farm.error.FarmError;
import farm.request_model.update_animal.UpdateAnimalModel;

@Component
public class CowValidator extends AnimalValidator {

	public CowValidator() {

	}
	
	@Override
	public ArrayList<FarmError> validateUpdate(UpdateAnimalModel animalModel, Animal animal) {
		
		ArrayList<FarmError> errors = super.validateUpdate(animalModel, animal);
		
		if(!errors.isEmpty()) {
			return errors;
		}
		
		if(animal.getAnimalSex().equals(AnimalSexType.Male)) {
			errors.add(new FarmError(ErrorType.IdRelatedToBull));
		}
		
		return errors;
	}
}
