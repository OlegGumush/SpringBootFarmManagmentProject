package farm.entity.animal;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import farm.enums.AnimalSexType;
import farm.enums.AnimalType;

@Entity
@DiscriminatorValue(value = AnimalType.BULL)
public class Bull extends Animal {

	public Bull() {
		super(AnimalSexType.Male);
	}
}
