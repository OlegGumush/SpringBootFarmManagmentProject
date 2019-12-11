package farm.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import farm.bl.AnimalBL;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
public class AnimalController {

	@Autowired
	private AnimalBL animalsBL;
	
	@RequestMapping(value = "/animals", params = { "page", "size" }, method = RequestMethod.GET)
	@ApiOperation(value = "Get all animals", notes="notes", nickname = "AllAnimals")
	public ResponseEntity<FarmResult> getAllAnimals(@RequestParam(name = "page", defaultValue = "0") int page,
												    @RequestParam(name = "size", defaultValue = "100") int size,
												    @RequestParam(name = "sort", defaultValue = "insertedDateTime") String orderBy) {
		
		try {
			FarmResult result = animalsBL.getAllAnimals(page, size, orderBy);
		
			if(result.isSucceeded()) {
				return new ResponseEntity<FarmResult>(result, HttpStatus.OK);			
			}
			
			return new ResponseEntity<FarmResult>(result, HttpStatus.BAD_REQUEST);				

		} catch (Exception e) {
			return new ResponseEntity<FarmResult>(HttpStatus.INTERNAL_SERVER_ERROR);
		}		
	}
	
	@RequestMapping(value = "/animals/{id}", method = RequestMethod.GET)
	@ApiOperation(value = "Get animal by id", notes="notes", nickname = "GetAnimal")
	public ResponseEntity<FarmResult> getAnimalById(@PathVariable long id) {
		
		try {
			FarmResult result = animalsBL.getAnimalById(id);
			
			if (result.getResult() == null) {
				return new ResponseEntity<FarmResult>(result, HttpStatus.NOT_FOUND);
			}
			
			return new ResponseEntity<FarmResult>(result, HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<FarmResult>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@RequestMapping(value = "/animals/{id}", method = RequestMethod.DELETE)
	@ApiOperation(value = "Delete animal by id", notes="notes", nickname = "DeleteAnimal")
	public ResponseEntity<FarmResult> deleteAnimalById(@PathVariable long id) {
		
		try {
			FarmResult result = animalsBL.deleteAnimalById(id);
			
			if (result.isSucceeded()) {
				 return new ResponseEntity<FarmResult>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<FarmResult>(result, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<FarmResult>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}	
	
	@RequestMapping(value = "/animals/importFromFile", method = RequestMethod.POST)
	@ApiOperation(value = "Import animals from file", notes="notes", nickname = "ImportAnimalsFromFile")
	public ResponseEntity<FarmResult> importAnimalsFromFile(@RequestBody MultipartFile file) {
		
		BufferedReader br;
		List<String> result1 = new ArrayList<>();
		try {

		     String line;
		     InputStream is = file.getInputStream();
		     br = new BufferedReader(new InputStreamReader(is));
		     while ((line = br.readLine()) != null) {
		          result1.add(line);
		          System.out.println(line);
		     }

		  } catch (IOException e) {
		    System.err.println(e.getMessage());       
		  }
		
		try {
			FarmResult result = null;
			
			if (result.isSucceeded()) {
				 return new ResponseEntity<FarmResult>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<FarmResult>(result, HttpStatus.NOT_FOUND);
		} catch (Exception e) {
			return new ResponseEntity<FarmResult>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}













