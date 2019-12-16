package farm.entity.user;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import farm.entity.BaseEntity;
import farm.enums.RoleType;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	
	@NotNull
    private String username;

	@NotNull
    private String password;
    
	@NotNull
    private String email;

	@NotNull
    private String name;
	
    @NotNull
	@Enumerated(value = EnumType.STRING)
    private RoleType role;

    public User() {
  
    }

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public RoleType getRole() {
		return role;
	}

	public void setRole(RoleType role) {
		this.role = role;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}