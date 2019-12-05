package farm.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import farm.enums.AnimalSex;
import farm.enums.AnimalType;

@Entity
@DiscriminatorValue(value = AnimalType.COW)
public class Cow extends Animal {

	public Cow() {
		super(AnimalSex.Female);
	}
}
