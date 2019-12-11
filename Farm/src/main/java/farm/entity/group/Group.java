package farm.entity.group;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import farm.entity.BaseEntity;

@Entity
@Table(name = "animal_group")
public class Group extends BaseEntity {

	public Group() {
		
	}
	
	@NotNull
	private String groupName;
	
	@NotNull
	private long groupNumber;

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Long getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(Long groupNumber) {
		this.groupNumber = groupNumber;
	}
}
