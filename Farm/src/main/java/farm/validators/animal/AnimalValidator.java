package farm.validators.animal;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import farm.bl.AnimalBL;
import farm.bl.GroupBL;
import farm.entity.animal.Animal;
import farm.enums.ErrorType;
import farm.model.animal.AnimalModel;
import farm.result.FarmResult;

@Component
public abstract class AnimalValidator implements IAnimalValidator {

	@Autowired
	private AnimalBL animalBL;
	
	@Autowired
	private GroupBL groupBL;
	
	public static final int ANIMAL_NAME_MAXIMUM_LENGTH = 20;
	
	
	@Override
	public FarmResult validateCreate(AnimalModel animalModel) {
				
		FarmResult result = validateBase(animalModel);
		
		if(!result.isSucceeded()) {
			return result;
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
		
		FarmResult result = validateBase(animalModel);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		if(isAnimalExistsByNameExceptId(animalModel.AnimalName, animal.getId())) {
			return new FarmResult(ErrorType.AnimalNameAlreadyExists, "AnimalName");
		}
		
		return new FarmResult();
	}

	@Override
	public FarmResult validateDelete(AnimalModel model, Animal animal) {
		return new FarmResult();
	}
	
	private boolean isAnimalExistsByName(String animalName) {
		
		return animalBL.isAnimalExistsByName(animalName);  
	}
	
	private boolean isAnimalExistsByNameExceptId(String animalName, long id) {
		
		return animalBL.isAnimalExistsByNameExceptId(animalName, id);
	}

	private FarmResult validateBase(AnimalModel animalModel) {
		
		if (animalModel == null) {
			return new FarmResult(ErrorType.AnimalModelIsEmpty, "AnimalName");
		}
		
		if(Strings.isEmpty(animalModel.AnimalName)) {
			
			return new FarmResult(ErrorType.AnimalNameCannotBeEmpty, "AnimalName");
		}
		
		if(animalModel.AnimalName.length() > ANIMAL_NAME_MAXIMUM_LENGTH) {
			return new FarmResult(ErrorType.AnimalNameCannotBeBiggerThanThreshold, "AnimalName");
		}
		
		if(animalModel.GroupId == null) {
			return new FarmResult(ErrorType.GroupIdCannotBeEmpty, "GroupId");
		}
		
		if(!groupBL.isGroupExistById(animalModel.GroupId)) {
			return new FarmResult(ErrorType.GroupDoesNotExists, "GroupId");
		}
		
		return new FarmResult();
	}
}
