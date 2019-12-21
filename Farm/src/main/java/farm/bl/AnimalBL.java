package farm.bl;

import static farm.enums.CacheType.ANIMAL_CACHE;
import static farm.enums.CacheType.OPERATION_FAILED;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import farm.entity.animal.Animal;
import farm.entity.animal.Bull;
import farm.entity.animal.Cow;
import farm.enums.EntityType;
import farm.enums.ErrorType;
import farm.error.FarmError;
import farm.repository.AnimalRepository;
import farm.request_model.animal.CerateBullModel;
import farm.request_model.animal.CreateAnimalModel;
import farm.request_model.animal.CreateCowModel;
import farm.request_model.animal.UpdateAnimalModel;
import farm.request_model.animal.UpdateBullModel;
import farm.request_model.animal.UpdateCowModel;
import farm.result.FarmResult;
import farm.validators.ValidatorFactory;
import farm.validators.animal.IAnimalValidator;

@Service
@Transactional(value = TxType.REQUIRED)
public class AnimalBL {
	
	@Autowired
	protected AnimalRepository animalRepository;
	
	@Autowired
	protected ValidatorFactory validatorFactory;	
	
	@Autowired
	protected GroupBL groupBL;
	
	public FarmResult<List<Animal>> getAllAnimals(int page, int size, String orderBy) {
		
		List<Animal> animals = (ArrayList<Animal>) animalRepository.findAllWithPageAndOrder(page, size, orderBy);	
				
		return new FarmResult<>(animals);
	}

	@Cacheable(value = ANIMAL_CACHE, key = "#animalId", unless = OPERATION_FAILED)
	public FarmResult<Animal> getAnimalById(long animalId) {
				
		Animal animal =  animalRepository.findById(animalId);

		if (animal == null) {
			return new FarmResult<Animal>(new FarmError(ErrorType.AnimalNotExists));
		}
		
		return new FarmResult<>(animal);
	}
	
	@CacheEvict(value = ANIMAL_CACHE, key = "#animalId")
	public FarmResult<Animal> deleteAnimalById(long animalId) {
		
		Animal animal = animalRepository.removeById(animalId);

		if (animal == null) {
			return new FarmResult<Animal>(new FarmError(ErrorType.AnimalNotExists));
		}
		
		return new FarmResult<>(animal);
	}

	public FarmResult<Cow> createCow(CreateCowModel cowModel) {
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(EntityType.Cow);
		ArrayList<FarmError> errors = animalValidator.validateCreate(cowModel);
		
		if(!errors.isEmpty()) {
			return new FarmResult<>(errors);
		}
		
		Cow cow = new Cow();   

		setCreateCommonFields(cowModel, cow);
		
		animalRepository.insert(cow);
		
		return new FarmResult<>(cow);
	}
	
	public FarmResult<Bull> createBull(CerateBullModel bullModel) {
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(EntityType.Bull);
		ArrayList<FarmError> errors = animalValidator.validateCreate(bullModel);
		
		if(!errors.isEmpty()) {
			return new FarmResult<>(errors);
		}
		
		Bull bull = new Bull();
		
		setCreateCommonFields(bullModel, bull);

		animalRepository.insert(bull);
		
		return new FarmResult<>(bull);
	}
	
	@CachePut(value = ANIMAL_CACHE, key = "#animalId", unless = OPERATION_FAILED)
	public FarmResult<Cow> updateCow(UpdateCowModel cowModel, long animalId) {
		
		
		FarmResult<Animal> result = getAnimalById(animalId);
		Animal cow = result.getResult();
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(EntityType.Cow);
		ArrayList<FarmError> errors = animalValidator.validateUpdate(cowModel, cow);
		
		if(!errors.isEmpty()) {
			return new FarmResult<>(errors);
		}
		
		setUpdateCommonFields(cowModel, cow);
		
		animalRepository.update(cow);
		
		return new FarmResult<>((Cow)cow);
	}
	
	@CachePut(value = ANIMAL_CACHE, key = "#animalId", unless = OPERATION_FAILED)
	public FarmResult<Bull> updateBull(UpdateBullModel bullModel, long animalId) {
		
		FarmResult<Animal> result = getAnimalById(animalId);
		Animal bull = result.getResult();
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(EntityType.Bull);
		ArrayList<FarmError> errors = animalValidator.validateUpdate(bullModel, bull);
		
		if(!errors.isEmpty()) {
			return new FarmResult<>(errors);
		}
		
		setUpdateCommonFields(bullModel, bull);
		
		animalRepository.update(bull);
		
		return new FarmResult<Bull>((Bull)bull);
	}

	public FarmResult<List<Cow>> getAllCows(int page, int size, String orderBy) {

		List<Animal> animals = (ArrayList<Animal>) animalRepository.findAllCows(page, size, orderBy);	
		
		return new FarmResult<>(animals.stream().map(animal -> (Cow)animal).collect(Collectors.toList()));
	}

	public FarmResult<List<Bull>> getAllBulls(int page, int size, String orderBy) {

		List<Animal> animals = (ArrayList<Animal>) animalRepository.findAllBulls(page, size, orderBy);	
		
		return new FarmResult<>(animals.stream().map(animal -> (Bull)animal).collect(Collectors.toList()));
	}
	
	public boolean isAnimalExistsByNameExceptId(String animalName, long id) {
		
		return animalRepository.isAnimalExistsByNameExceptId(animalName, id);
	}
	
	public boolean isAnimalExistsByName(String animalName) {
		
		return animalRepository.isAnimalExistsByNameExceptId(animalName, 0);
	}

	private void setUpdateCommonFields(UpdateAnimalModel animalModel, Animal animal) {
		
		animal.setAnimalName(animalModel.AnimalName);
	}
	
	private void setCreateCommonFields(CreateAnimalModel animalModel, Animal animal) {

		animal.setAnimalName(animalModel.AnimalName);
		animal.setGroup(groupBL.findGroupById(animalModel.GroupId));
	}
}