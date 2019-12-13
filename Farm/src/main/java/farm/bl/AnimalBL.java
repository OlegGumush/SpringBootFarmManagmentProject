package farm.bl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import farm.entity.animal.Animal;
import farm.entity.animal.Bull;
import farm.entity.animal.Cow;
import farm.enums.EntityType;
import farm.error.FarmError;
import farm.repository.AnimalRepository;
import farm.request_model.animal.AnimalModel;
import farm.request_model.animal.BullModel;
import farm.request_model.animal.CowModel;
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
	
	public FarmResult<Animal> getAnimalById(long id) {
				
		Animal animal =  animalRepository.findById(id);

		return new FarmResult<>(animal);
	}
	
	public FarmResult<Animal> deleteAnimalById(long id) {
		
		Animal animal = animalRepository.removeById(id);

		return new FarmResult<>(animal);
	}

	public FarmResult<Cow> createCow(CowModel cowModel) {
		
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
	
	public FarmResult<Bull> createBull(BullModel bullModel) {
		
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
	
	public FarmResult<Cow> updateCow(CowModel cowModel, long id) {
		
		FarmResult<Animal> result = getAnimalById(id);
		Animal cow = result.getResult();
		
		IAnimalValidator animalValidator = (IAnimalValidator) validatorFactory.getValidator(EntityType.Cow);
		ArrayList<FarmError> errors = animalValidator.validateUpdate(cowModel, cow);
		
		if(!result.isSucceeded()) {
			return new FarmResult<>(errors);
		}
		
		setUpdateCommonFields(cowModel, cow);
		
		animalRepository.update(cow);
		
		return new FarmResult<>((Cow)cow);
	}
	
	public FarmResult<Bull> updateBull(BullModel bullModel, long id) {
		
		FarmResult<Animal> result = getAnimalById(id);
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

	private void setUpdateCommonFields(AnimalModel animalModel, Animal animal) {
		
		animal.setAnimalName(animalModel.AnimalName);
		animal.setGroup(groupBL.findGroupById(animalModel.GroupId));
	}
	
	private void setCreateCommonFields(AnimalModel animalModel, Animal animal) {

		animal.setAnimalName(animalModel.AnimalName);
		animal.setGroup(groupBL.findGroupById(animalModel.GroupId));
	}
}