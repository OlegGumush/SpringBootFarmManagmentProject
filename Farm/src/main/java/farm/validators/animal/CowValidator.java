package farm.validators.animal;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import farm.entity.animal.Animal;
import farm.enums.AnimalSex;
import farm.enums.ErrorType;
import farm.error.FarmError;
import farm.request_model.animal.AnimalModel;

@Component
public class CowValidator extends AnimalValidator {

	public CowValidator() {

	}
	
	@Override
	public ArrayList<FarmError> validateUpdate(AnimalModel animalModel, Animal animal) {
		
		ArrayList<FarmError> errors = super.validateUpdate(animalModel, animal);
		
		if(!errors.isEmpty()) {
			return errors;
		}
		
		if(animal.getAnimalSex().equals(AnimalSex.Male)) {
			errors.add(new FarmError(ErrorType.CannotUpdateCowWithBullModel));
		}
		
		return errors;
	}
}
