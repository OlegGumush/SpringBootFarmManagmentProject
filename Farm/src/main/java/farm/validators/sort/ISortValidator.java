package farm.validators.sort;

import farm.result.FarmResult;
import farm.validators.IValidator;

public interface ISortValidator extends IValidator {

	FarmResult validateSort(String orderBy);
}
