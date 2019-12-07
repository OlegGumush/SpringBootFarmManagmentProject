package farm.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import farm.enums.EntityType;
import farm.exception.FarmException;
import farm.model.BaseModel;
import farm.validators.animal.BullValidator;
import farm.validators.animal.CowValidator;
import farm.validators.sort.BaseSortValidator;

@Component
public class ValidatorFactory {

	@Autowired
	private CowValidator cowValidator;
	
	@Autowired
	private BullValidator bullValidator;
	
	public ValidatorFactory() {
		
	}

	public IValidator getValidator(BaseModel model) {
		
		switch (model.type) {
		case bullModel:
			return bullValidator;
		case cowModel:
			return cowValidator;
		default:
			throw new FarmException("ValidatorFactory cannot find model type " + model.type);
		}
	}
	
	public IValidator getSortValidator(EntityType entityType) {
		
		switch (entityType) {
		case Animal:
		case Bull:
		case Cow:
			return new BaseSortValidator();
		default:
			throw new FarmException("ValidatorFactory cannot find entity type " + entityType);
		}	
	}
}
