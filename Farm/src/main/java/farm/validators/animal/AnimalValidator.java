package farm.validators.animal;

import java.util.ArrayList;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import farm.bl.AnimalBL;
import farm.bl.GroupBL;
import farm.entity.animal.Animal;
import farm.enums.ErrorType;
import farm.error.FarmError;
import farm.request_model.animal.AnimalModel;

@Component
public abstract class AnimalValidator implements IAnimalValidator {

	@Autowired
	private AnimalBL animalBL;
	
	@Autowired
	private GroupBL groupBL;
	
	public static final int ANIMAL_NAME_MAXIMUM_LENGTH = 20;
	
	
	@Override
	public ArrayList<FarmError> validateCreate(AnimalModel animalModel) {
				
		 ArrayList<FarmError> errors = validateBase(animalModel);
		
		if(!errors.isEmpty()) {
			return errors;
		
		} else if(isAnimalExistsByName(animalModel.AnimalName)) {
			errors.add(new FarmError(ErrorType.AnimalNameAlreadyExists, "AnimalName"));
		}
		
		return errors;
	}

	@Override
	public ArrayList<FarmError> validateUpdate(AnimalModel animalModel, Animal animal) {
		
		 ArrayList<FarmError> errors = validateBase(animalModel);

		if(animal == null) {
			errors.add(new FarmError(ErrorType.AnimalNotExists, "Id"));
			return errors;
		}
		
		errors = validateBase(animalModel);
		
		if(!errors.isEmpty()) {
			
			return errors;
			
		} else if(isAnimalExistsByNameExceptId(animalModel.AnimalName, animal.getId())) {
			
			errors.add(new FarmError(ErrorType.AnimalNameAlreadyExists, "AnimalName"));
		}
		
		return errors;
	}
	
	private boolean isAnimalExistsByName(String animalName) {
		
		return animalBL.isAnimalExistsByName(animalName);  
	}
	
	private boolean isAnimalExistsByNameExceptId(String animalName, long id) {
		
		return animalBL.isAnimalExistsByNameExceptId(animalName, id);
	}

	private ArrayList<FarmError> validateBase(AnimalModel animalModel) {
		
		ArrayList<FarmError> errors = new ArrayList<>();
		
		if (animalModel == null) {
			
			errors.add(new FarmError(ErrorType.AnimalModelIsEmpty, "AnimalName"));
		
		} else if(Strings.isEmpty(animalModel.AnimalName)) {
			
			errors.add(new FarmError(ErrorType.AnimalNameCannotBeEmpty, "AnimalName"));
			
		} else if(animalModel.AnimalName.length() > ANIMAL_NAME_MAXIMUM_LENGTH) {
			
			errors.add(new FarmError(ErrorType.AnimalNameCannotBeBiggerThanThreshold, "AnimalName"));
			
		} else if(animalModel.GroupId == null) {
			
			errors.add(new FarmError(ErrorType.GroupIdCannotBeEmpty, "GroupId"));
			
		} else if(!groupBL.isGroupExistById(animalModel.GroupId)) {
			
			errors.add(new FarmError(ErrorType.GroupNotNotExists, "GroupId"));
		}
		
		return errors;
	}
}
