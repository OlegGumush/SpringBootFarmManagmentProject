package farm.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import farm.enums.AnimalSex;
import farm.enums.AnimalType;

@Entity
@DiscriminatorValue(value = AnimalType.Cow)
public class Cow extends Animal {

	public Cow() {
		super(AnimalSex.Female);
	}
}
