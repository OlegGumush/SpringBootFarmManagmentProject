package farm.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import farm.entity.user.User;
import farm.repository.query.Filter;
import farm.repository.query.SearchParams;

@Repository
public class UserRepository extends BaseRepository<User>{

	public UserRepository() {
		super(User.class);
	}

	public User findUserByName(String name) {

		SearchParams sp = new SearchParams()
				.where(Filter.equal("username", name));	

		List<User> users =  findAll(sp);
		
		if (users.isEmpty()) {
			return null;
		}
		
		return users.get(0);
	}
}
