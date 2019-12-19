package farm.enums;

public enum RoleType {

	ROLE_SYSTEM,
	ROLE_ADMIN,
	ROLE_FARMER;
	
	public static final String ADMIN = "hasRole('ROLE_ADMIN')";
	public static final String FARMER = "hasRole('ROLE_FARMER')";
	public static final String SYSTEM = "hasRole('ROLE_SYSTEM')";
	public static final String OR = " or ";
}
