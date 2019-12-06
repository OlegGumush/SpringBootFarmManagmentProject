package farm.entity.animal;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import farm.entity.BaseEntity;
import farm.enums.AnimalSex;

@Entity
@Table(name = "animal")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animalType")
public abstract class Animal extends BaseEntity {
	
	public Animal(AnimalSex animalSex) {
		this.animalSex = animalSex;
	}
	
	@NotNull
	private String animalName;
	
	@NotNull
	@Enumerated(value = EnumType.STRING)
	private AnimalSex animalSex;

	public String getAnimalName() {
		return animalName;
	}

	public void setAnimalName(String animalName) {
		this.animalName = animalName;
	}

	public AnimalSex getAnimalSex() {
		return animalSex;
	}

	public void setAnimalSex(AnimalSex animalSex) {
		this.animalSex = animalSex;
	}
}
