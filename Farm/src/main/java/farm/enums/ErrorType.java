package farm.enums;

public enum ErrorType {

	// Animal
	AnimalNotExists,
	AnimalNameAlreadyExists,
	AnimalNameCannotBeEmpty,
	AnimalModelIsEmpty,
	AnimalNameCannotBeBiggerThanThreshold,
	IdRelatedToCow,
	IdRelatedToBull,
	
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