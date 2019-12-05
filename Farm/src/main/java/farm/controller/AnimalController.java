package farm.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.AnimalBL;
import farm.entity.Animal;

@RestController
public class AnimalController {

	@Autowired
	private AnimalBL animalsBL;
	
	@RequestMapping(value = "/animals", params = { "page", "size" }, method = RequestMethod.GET)
	public ResponseEntity<ArrayList<Animal>> getAllAnimals(@RequestParam("page") int page, @RequestParam("size") int size) {
		
		ArrayList<Animal> animals = animalsBL.getAllAnimals(page, size);
		
		return new ResponseEntity<ArrayList<Animal>>(animals, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/animals/{id}", method = RequestMethod.GET)
	public ResponseEntity<Animal> getAnimalById(@PathVariable long id) {
		
		Animal animal = animalsBL.getAnimalById(id);
		
		if (animal == null) {
			return new ResponseEntity<Animal>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Animal>(animal, HttpStatus.OK);
	}	
	
	@RequestMapping(value = "/animals/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Object> deleteAnimalById(@PathVariable long id) {
		
		boolean isDeleted = animalsBL.deleteAnimalById(id);
		
		if (isDeleted) {
			 return new ResponseEntity<Object>(HttpStatus.OK);
		}
		
		return new ResponseEntity<Object>(HttpStatus.NOT_FOUND);
	}	
}













