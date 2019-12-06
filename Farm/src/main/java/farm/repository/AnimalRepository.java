package farm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import farm.entity.animal.Animal;
import farm.enums.AnimalSex;
import farm.repository.query.Filter;
import farm.repository.query.SearchParams;

@Repository
public class AnimalRepository extends BaseRepository<Animal>  {

	public AnimalRepository() {
		super(Animal.class);
	}	
	
	
	public List<Animal> findAllCows(int page, int size, String orderBy) {
		
		SearchParams sp = new SearchParams()
								.where(Filter.equal("animalSex", AnimalSex.Female));	
		
		return findAllWithPageAndOrder(page, size, orderBy, sp);
	}
	
	public List<Animal> findAllBulls(int page, int size, String orderBy) {
		
		SearchParams sp = new SearchParams()
								.where(Filter.equal("animalSex", AnimalSex.Male));	
		
		return findAllWithPageAndOrder(page, size,orderBy, sp);
	}

	public boolean isAnimalExistsByNameExceptId(String name, long id) {
		
		SearchParams sp = new SearchParams()
							.where(Filter.equal("animalName", name))
							.where(Filter.notEqual("id", id));
		
		List<Animal> result = findAll(sp);
		
		if(result.isEmpty()) {
			return false;
		}
		
		return true;
	}
}