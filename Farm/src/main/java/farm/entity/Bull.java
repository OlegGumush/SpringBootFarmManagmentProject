package farm.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import farm.enums.AnimalSex;
import farm.enums.AnimalType;

@Entity
@DiscriminatorValue(value = AnimalType.Bull)
public class Bull extends Animal {

	public Bull() {
		super(AnimalSex.Male);
	}
}
