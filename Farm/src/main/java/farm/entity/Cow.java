package farm.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import farm.enums.AnimalSex;
import farm.enums.AnimalType;

@Entity
@DiscriminatorValue(value = AnimalType.Cow)
public class Cow extends Animal {

	private boolean isPregnant;

	public Cow() {
		super(AnimalSex.Female);
	}
	
	public boolean isPregnant() {
		return isPregnant;
	}

	public void setPregnant(boolean isPregnant) {
		this.isPregnant = isPregnant;
	}
}
