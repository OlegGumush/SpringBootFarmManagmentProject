package farm.validators.animal;

import org.springframework.stereotype.Component;

import farm.entity.animal.Animal;
import farm.enums.AnimalSex;
import farm.enums.ErrorType;
import farm.model.animal.AnimalModel;
import farm.result.FarmResult;

@Component
public class BullValidator extends AnimalValidator {

	public FarmResult validateUpdate(AnimalModel animalModel, Animal animal) {
		
		FarmResult result = super.validateUpdate(animalModel, animal);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		if(animal.getAnimalSex().equals(AnimalSex.Female)) {
			return new FarmResult(ErrorType.CannotUpdateBullWithCowModel);
		}
		
		return new FarmResult();
	}
}
