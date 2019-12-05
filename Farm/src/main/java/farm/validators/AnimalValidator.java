package farm.validators;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import farm.bl.AnimalBL;
import farm.entity.Animal;
import farm.enums.ErrorType;
import farm.model.AnimalModel;
import farm.result.FarmResult;

@Component
public abstract class AnimalValidator implements IAnimalValidator {

	@Autowired
	private AnimalBL animalBL;
	
	public static final int ANIMAL_NAME_MAXIMUM_LENGTH = 20;
	
	@Override
	public FarmResult validateCreate(AnimalModel animalModel) {
				
		if(Strings.isEmpty(animalModel.AnimalName)) {
			
			return new FarmResult(ErrorType.AnimalNameCannotBeEmpty, "AnimalName");
		}
		
		if(isValidAnimalNameLength(animalModel.AnimalName)) {
			return new FarmResult(ErrorType.AnimalNameCannotBeBiggerThanThreshold, "AnimalName");
		}
		
		if(isAnimalExistsByName(animalModel.AnimalName)) {
			return new FarmResult(ErrorType.AnimalNameAlreadyExists, "AnimalName");
		}
		
		return new FarmResult();
	}

	@Override
	public FarmResult validateUpdate(AnimalModel animalModel, Animal animal) {
		
		if(animal == null) {
			return new FarmResult(ErrorType.AnimalNotExists);
		}
		
		if(Strings.isEmpty(animalModel.AnimalName)) {
			return new FarmResult(ErrorType.AnimalNameCannotBeEmpty, "AnimalName");
		}
		
		if(isValidAnimalNameLength(animalModel.AnimalName)) {
			return new FarmResult(ErrorType.AnimalNameCannotBeBiggerThanThreshold, "AnimalName");
		}
		
		if(isAnimalExistsByNameExceptId(animalModel.AnimalName, animal.getId())) {
			return new FarmResult(ErrorType.AnimalNameAlreadyExists, "AnimalName");
		}
		
		return new FarmResult();
	}

	@Override
	public FarmResult validateDelete(AnimalModel model, Animal animal) {
		return null;
	}
	
	private boolean isAnimalExistsByName(String animalName) {
		
		return animalBL.isAnimalExistsByName(animalName);  
	}
	
	private boolean isAnimalExistsByNameExceptId(String animalName, long id) {
		
		return animalBL.isAnimalExistsByNameExceptId(animalName, id);
	}
	
	private boolean isValidAnimalNameLength(String animalName) {
		return animalName.length() > ANIMAL_NAME_MAXIMUM_LENGTH;
	}
}
