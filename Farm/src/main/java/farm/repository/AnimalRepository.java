package farm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import farm.entity.Animal;
import farm.entity.BaseEntity;
import farm.enums.AnimalSex;
import farm.repository.query.Filter;
import farm.repository.query.SearchParams;

@Repository
public class AnimalRepository extends BaseRepository<Animal>  {

	public AnimalRepository() {
		super(Animal.class);
	}	
	
	
	public List<Animal> findAllCows(int page, int size) {
		
		SearchParams sp = new SearchParams().where(Filter.equal("animalSex", AnimalSex.Female));		
		
		return findAll(page, size, sp);
	}
	
	public List<Animal> findAllBulls(int page, int size) {
		
		SearchParams sp = new SearchParams().where(Filter.equal("animalSex", AnimalSex.Male));		
		
		return findAll(page, size, sp);
	}

	// not good name
	public boolean isAnimalExistsByName(String name, long id) {
		
		SearchParams sp = new SearchParams()
							.where(Filter.equal("animalName", name))
							.where(Filter.notEqual("id", id));
		
		List<Animal> result = find(sp);
		
		if(result.isEmpty()) {
			return false;
		}
		
		return true;
	}
}