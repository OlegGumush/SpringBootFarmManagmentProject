package farm.response_model.animal;

import farm.entity.animal.Animal;
import farm.enums.AnimalSex;
import farm.response_model.BaseResponseModel;
import farm.response_model.group.GroupResponseModel;

public class AnimalResponseModel extends BaseResponseModel {

	public String AnimalName;
	
	public AnimalSex AnimalSex;
	
	public GroupResponseModel Group;
	
	public AnimalResponseModel(Animal animal) {
		
		super(animal.getId(), animal.getInsertedDateTime());
		this.AnimalName = animal.getAnimalName();
		this.Group = new GroupResponseModel(animal.getGroup());
		this.AnimalSex = animal.getAnimalSex();
	}
}
