package farm.repository;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import farm.entity.animal.Animal;
import farm.enums.AnimalSexType;
import farm.repository.query.Filter;
import farm.repository.query.SearchParams;

@Repository
@Transactional(value = TxType.REQUIRED)
public class AnimalRepository extends BaseRepository<Animal>  {

	public AnimalRepository() {
		super(Animal.class);
	}	
	
	
	public List<Animal> findAllCows(int page, int size, String orderBy) {
		
		SearchParams sp = new SearchParams()
								.where(Filter.equal("animalSex", AnimalSexType.Female));	
		
		return findAllWithPageAndOrder(page, size, orderBy, sp);
	}
	
	public List<Animal> findAllBulls(int page, int size, String orderBy) {
		
		SearchParams sp = new SearchParams()
								.where(Filter.equal("animalSex", AnimalSexType.Male));	
		
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