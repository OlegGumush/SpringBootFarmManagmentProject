package farm.enums;

public enum ErrorType {

	// Animal
	AnimalNotExists,
	AnimalNameAlreadyExists,
	AnimalNameCannotBeEmpty,
	AnimalModelIsEmpty,
	AnimalNameCannotBeBiggerThanThreshold,
	CannotUpdateCowWithBullModel,
	CannotUpdateBullWithCowModel,
	
	// Sort
	InvalidSortParameter,
	
	// Group
	GroupNameCannotBeEmpty,
	GroupNumberCannotBeEmpty,
	GroupNameCannotBeBiggerThanThreshold,
	GroupNumberCannotBeNegative,
	GroupNumberCannotBeZeroItRelatedToDefaultGroup,
	GroupNameAlreadyExists,
	GroupNumberAlreadyExists,
	GroupIdCannotBeEmpty,
	GroupNotNotExists
	;
}