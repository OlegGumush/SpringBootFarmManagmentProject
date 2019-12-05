package farm.bl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farm.entity.Animal;
import farm.entity.Bull;
import farm.entity.Cow;
import farm.model.AnimalModel;
import farm.model.BullModel;
import farm.model.CowModel;
import farm.repository.AnimalRepository;
import farm.result.FarmResult;
import farm.validators.IAnimalValidator;
import farm.validators.ValidatorFactory;

@Service
public class AnimalBL {
	
	@Autowired
	private AnimalRepository animalRepository;
	
	@Autowired
	private ValidatorFactory validatorFactory;
	
	public ArrayList<Animal> getAllAnimals(int page, int size) {
		
		return (ArrayList<Animal>) animalRepository.findAll(page, size);
	}
	
	public Animal getAnimalById(long id) {
		
		return animalRepository.findById(id);
	}
	
	public boolean deleteAnimalById(long id) {
		
		if (animalRepository.findById(id) != null) {
			animalRepository.removeById(id);			
			return true;
		} 
		
		return false;
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
		
		result.setEntityId(cow.getId());
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
		
		result.setEntityId(bull.getId());
		return result;
	}
	
	public FarmResult updateCow(CowModel cowModel, long id) {
		
		Animal cow = getAnimalById(id);
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(cowModel);
		FarmResult result = animalValidator.validateUpdate(cowModel, cow);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		setUpdateCommonFields(cowModel, cow);
		
		animalRepository.update(cow);
		
		result.setEntityId(cow.getId());
		return result;
	}
	
	public FarmResult updateBull(BullModel bullModel, long id) {
		
		Animal bull = getAnimalById(id);
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(bullModel);
		FarmResult result = animalValidator.validateUpdate(bullModel, bull);
		
		if(!result.isSucceeded()) {
			return result;
		}
		
		setUpdateCommonFields(bullModel, bull);
		
		animalRepository.update(bull);
		
		result.setEntityId(bull.getId());
		return result;
	}

	public ArrayList<Animal> getAllCows(int page, int size) {

		return (ArrayList<Animal>) animalRepository.findAllCows(page, size);
	}

	public ArrayList<Animal> getAllBulls(int page, int size) {
		
		return (ArrayList<Animal>) animalRepository.findAllBulls(page, size);
	}
	
	public boolean isAnimalExistsByName(String animalName, long id) {
		
		return animalRepository.isAnimalExistsByName(animalName, id);
	}
	
	private void setUpdateCommonFields(AnimalModel animalModel, Animal animal) {
		
		animal.setAnimalName(animalModel.AnimalName);
	}
	
	private void setCreateCommonFields(AnimalModel animalModel, Animal animal) {

		animal.setAnimalName(animalModel.AnimalName);
	}


}

























