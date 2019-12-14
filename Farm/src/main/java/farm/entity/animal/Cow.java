package farm.entity.animal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import farm.enums.AnimalSexType;
import farm.enums.AnimalType;

@Entity
@DiscriminatorValue(value = AnimalType.COW)
public class Cow extends Animal {

	public Cow() {
		super(AnimalSexType.Female);
	}
}
