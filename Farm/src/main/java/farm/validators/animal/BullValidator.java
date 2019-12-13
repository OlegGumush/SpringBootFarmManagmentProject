package farm.validators.animal;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import farm.entity.animal.Animal;
import farm.enums.AnimalSex;
import farm.enums.ErrorType;
import farm.error.FarmError;
import farm.request_model.animal.AnimalModel;

@Component
public class BullValidator extends AnimalValidator {

	public ArrayList<FarmError> validateUpdate(AnimalModel animalModel, Animal animal) {
		
		ArrayList<FarmError> errors = super.validateUpdate(animalModel, animal);
		
		if(!errors.isEmpty()) {
			return errors;
		}
		
		if(animal.getAnimalSex().equals(AnimalSex.Female)) {
			errors.add(new FarmError(ErrorType.CannotUpdateBullWithCowModel));
		}
		
		return errors;
	}
}
