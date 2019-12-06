package farm.validators.animal;

import org.springframework.stereotype.Component;

import farm.entity.animal.Animal;
import farm.enums.AnimalSex;
import farm.enums.ErrorType;
import farm.model.AnimalModel;
import farm.result.FarmResult;

@Component
public class CowValidator extends AnimalValidator {

	public CowValidator() {

	}
	
	@Override
	public FarmResult validateUpdate(AnimalModel animalModel, Animal animal) {
		
		FarmResult result = super.validateUpdate(animalModel, animal);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		if(animal.getAnimalSex().equals(AnimalSex.Male)) {
			return new FarmResult(ErrorType.CannotUpdateCowWithBullModel);
		}
		
		return new FarmResult();
	}
}
