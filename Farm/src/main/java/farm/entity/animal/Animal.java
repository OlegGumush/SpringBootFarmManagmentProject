package farm.entity.animal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import farm.entity.BaseEntity;
import farm.entity.group.Group;
import farm.enums.AnimalSexType;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animalType")
public abstract class Animal extends BaseEntity {
	
	public Animal(AnimalSexType animalSex) {
		this.animalSex = animalSex;
	}
	
	@Column(insertable = false, updatable = false)
	private Long groupId;
	
	@ManyToOne(targetEntity = Group.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "groupId", referencedColumnName = "id")
	private Group group;
	
	@NotNull
	private String animalName;
	
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private AnimalSexType animalSex;

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public AnimalSexType getAnimalSex() {
		return animalSex;
	}

	public void setAnimalSex(AnimalSexType animalSex) {
		this.animalSex = animalSex;
	}
}
