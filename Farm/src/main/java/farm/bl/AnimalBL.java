package farm.bl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farm.entity.animal.Animal;
import farm.entity.animal.Bull;
import farm.entity.animal.Cow;
import farm.enums.EntityType;
import farm.model.AnimalModel;
import farm.model.BullModel;
import farm.model.CowModel;
import farm.repository.AnimalRepository;
import farm.result.FarmResult;
import farm.validators.ValidatorFactory;
import farm.validators.animal.IAnimalValidator;
import farm.validators.sort.BaseSortValidator;

@Service
public class AnimalBL {
	
	@Autowired
	protected AnimalRepository animalRepository;
	
	@Autowired
	protected ValidatorFactory validatorFactory;	
	
	public FarmResult getAllAnimals(int page, int size, String orderBy) {
		
		BaseSortValidator sortValidator = (BaseSortValidator) validatorFactory.getSortValidator(EntityType.Animal);
		FarmResult result = sortValidator.validateSort(orderBy);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		List<Animal> animals = (ArrayList<Animal>) animalRepository.findAllWithPageAndOrder(page, size, orderBy);	
		result.setResult((Serializable) animals);
		
		return result;
	}
	
	public FarmResult getAnimalById(long id) {
		
		FarmResult result = new FarmResult();
		
		Animal animal =  animalRepository.findById(id);

		result.setResult(animal);
		if(animal == null) {
			result.setSucceeded(false);;			
		}
		
		return result;
	}
	
	public FarmResult deleteAnimalById(long id) {
		
		FarmResult result = new FarmResult();
		
		if (animalRepository.findById(id) != null) {
			animalRepository.removeById(id);			
			result.setResult(true);
		} 
		
		result.setResult(false, false);
		return result;
	}

	public FarmResult createCow(CowModel cowModel) {
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(cowModel);
		FarmResult result = animalValidator.validateCreate(cowModel);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		Cow cow = new Cow();   

		setCreateCommonFields(cowModel, cow);
		
		animalRepository.insert(cow);
		
		result.setResult(cow.getId());
		return result;
	}
	
	public FarmResult createBull(BullModel bullModel) {
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(bullModel);
		FarmResult result = animalValidator.validateCreate(bullModel);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		Animal bull = new Bull();
		
		setCreateCommonFields(bullModel, bull);

		animalRepository.insert(bull);
		
		result.setResult(bull.getId());
		return result;
	}
	
	public FarmResult updateCow(CowModel cowModel, long id) {
		
		FarmResult result = getAnimalById(id);
		Animal cow = (Animal)result.getResult();
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(cowModel);
		result = animalValidator.validateUpdate(cowModel, cow);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		setUpdateCommonFields(cowModel, cow);
		
		animalRepository.update(cow);
		
		result.setResult(cow.getId());
		return result;
	}
	
	public FarmResult updateBull(BullModel bullModel, long id) {
		
		FarmResult result = getAnimalById(id);
		Animal bull = (Bull)result.getResult();
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(bullModel);
		result = animalValidator.validateUpdate(bullModel, bull);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		setUpdateCommonFields(bullModel, bull);
		
		animalRepository.update(bull);
		
		result.setResult(bull.getId());
		return result;
	}

	public FarmResult getAllCows(int page, int size, String orderBy) {

		
		BaseSortValidator sortValidator = (BaseSortValidator) validatorFactory.getSortValidator(EntityType.Cow);
		FarmResult result = sortValidator.validateSort(orderBy);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		List<Animal> animals = (ArrayList<Animal>) animalRepository.findAllCows(page, size, orderBy);	
		result.setResult((Serializable) animals);
		
		return result;
	}

	public FarmResult getAllBulls(int page, int size, String orderBy) {
		
		BaseSortValidator sortValidator = (BaseSortValidator) validatorFactory.getSortValidator(EntityType.Bull);
		FarmResult result = sortValidator.validateSort(orderBy);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		List<Animal> animals = (ArrayList<Animal>) animalRepository.findAllBulls(page, size, orderBy);	
		result.setResult((Serializable) animals);
		
		return result;
	}
	
	public boolean isAnimalExistsByNameExceptId(String animalName, long id) {
		
		return animalRepository.isAnimalExistsByNameExceptId(animalName, id);
	}
	
	public boolean isAnimalExistsByName(String animalName) {
		
		return animalRepository.isAnimalExistsByNameExceptId(animalName, 0);
	}

	private void setUpdateCommonFields(AnimalModel animalModel, Animal animal) {
		
		animal.setAnimalName(animalModel.AnimalName);
	}
	
	private void setCreateCommonFields(AnimalModel animalModel, Animal animal) {

		animal.setAnimalName(animalModel.AnimalName);
	}
}

























