package farm.enums;

public enum ErrorType {

	// Animal
	AnimalNotExists,
	AnimalNameAlreadyExists,
	AnimalNameCannotBeEmpty,
	AnimalNameCannotBeBiggerThanThreshold,
	CannotUpdateCowWithBullModel,
	CannotUpdateBullWithCowModel,
	
	// Sort
	InvalidSortParameter
	;
}