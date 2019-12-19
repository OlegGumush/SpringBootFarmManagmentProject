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
	
	// Group
	GroupNameCannotBeEmpty,
	GroupNumberCannotBeEmpty,
	GroupNameCannotBeBiggerThanThreshold,
	GroupNumberCannotBeNegative,
	GroupNumberCannotBeZeroItRelatedToDefaultGroup,
	GroupNameAlreadyExists,
	GroupNumberAlreadyExists,
	GroupIdCannotBeEmpty,
	GroupNotNotExists,
	
	// User
	UserNotExists,
	BadCredentials,
	UserModelIsEmpty,
	UserNameCannotBeEmpty,
	UserNameCannotBeBiggerThanThreshold,
	UserUsernameCannotBeEmpty,
	UserUsernameCannotBeBiggerThanThreshold,
	UserEmailCannotBeEmpty,
	UserEmailCannotBeBiggerThanThreshold,
	UserPasswordCannotBeEmpty,
	UserPasswordCannotBeBiggerOrSmallerThanThreshold,
	UserEmailAlreadyExists,
	UserUsernameAlreadyExists
	;
}