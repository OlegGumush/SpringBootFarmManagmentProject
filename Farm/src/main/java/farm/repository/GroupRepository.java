package farm.repository;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.stereotype.Repository;

import farm.entity.group.Group;
import farm.repository.query.Filter;
import farm.repository.query.SearchParams;

@Repository
@Transactional(value = TxType.REQUIRED)
public class GroupRepository extends BaseRepository<Group>{

	public GroupRepository() {
		super(Group.class);
	}	
	
	public boolean isGroupExistsByNameExceptId(String groupName, long id) {
		
		SearchParams sp = new SearchParams()
							.where(Filter.equal("groupName", groupName))
							.where(Filter.notEqual("id", id));
		
		List<Group> result = findAll(sp);
		
		if(result.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	public boolean isGroupExistsByNumberExceptId(long groupNumber, long id) {
		
		SearchParams sp = new SearchParams()
							.where(Filter.equal("groupNumber", groupNumber))
							.where(Filter.notEqual("id", id));
		
		List<Group> result = findAll(sp);
		
		if(result.isEmpty()) {
			return false;
		}
		
		return true;
	}

	public boolean isGroupExistById(long id) {
		
	
		Group result = findById(id);
		
		if(result == null) {
			return false;
		}
		
		return true;
	}
}
