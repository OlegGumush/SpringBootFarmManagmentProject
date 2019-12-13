package farm.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import farm.enums.EntityType;
import farm.exception.FarmException;
import farm.validators.animal.BullValidator;
import farm.validators.animal.CowValidator;
import farm.validators.group.GroupValidator;

@Component
public class ValidatorFactory {

	@Autowired
	private CowValidator cowValidator;
	
	@Autowired
	private BullValidator bullValidator;
	
	@Autowired
	private GroupValidator groupValidator;
	
	public ValidatorFactory() {
		
	}

	public IValidator getValidator(EntityType entityType) {
		
		switch (entityType) {
		case Bull:
			return bullValidator;
		case Cow:
			return cowValidator;
		case Group:
			return groupValidator;
		default:
			throw new FarmException("ValidatorFactory cannot find model type " + entityType);
		}
	}
}
