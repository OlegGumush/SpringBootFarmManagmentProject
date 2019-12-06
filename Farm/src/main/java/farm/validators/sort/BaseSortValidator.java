package farm.validators.sort;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import farm.enums.ErrorType;
import farm.result.FarmResult;

public class BaseSortValidator implements ISortValidator {
	
	private static final Map<String, Boolean> validBaseSortOptions;    

    static {
        Map<String, Boolean> temp = new HashMap<String, Boolean>();
        temp.put("insertedDateTime", true);
        temp.put("-insertedDateTime", true);
        validBaseSortOptions = Collections.unmodifiableMap(temp);	
    }
	
	@Override
	public FarmResult validateSort(String orderBy) {
		
		if(validBaseSortOptions.get(orderBy) == null) {
			return new FarmResult(ErrorType.InvalidOrderByParameters, "orderBy");
		}
		return new FarmResult();
	}
}
