package farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.AnimalBL;
import farm.model.CowModel;
import farm.result.FarmResult;

@RestController
public class CowController {

	@Autowired
	private AnimalBL animalsBL;
	
	@RequestMapping(value = "/cows", method = RequestMethod.POST)
	public ResponseEntity<FarmResult> createBull(@RequestBody CowModel cowModel) {
		
		try {
			FarmResult result = animalsBL.createCow(cowModel);
			 
			if (result.isSucceeded()) {
				return new ResponseEntity<FarmResult>(result, HttpStatus.CREATED);
			}
	
			return new ResponseEntity<FarmResult>(result, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<FarmResult>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/cows/{id}", method = RequestMethod.PUT)
	public ResponseEntity<FarmResult> updateCow(@RequestBody CowModel cowModel, @PathVariable long id) {
		
		try {
			FarmResult result = animalsBL.updateCow(cowModel, id);
			
			if (result.isSucceeded()) {
				return new ResponseEntity<FarmResult>(result, HttpStatus.OK);
			}
			
			return new ResponseEntity<FarmResult>(result, HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<FarmResult>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/cows", params = { "page", "size" }, method = RequestMethod.GET)
	public ResponseEntity<FarmResult> getAllBulls(@RequestParam("page") int page,
												  @RequestParam("size") int size,
												  @RequestParam(name = "sort", defaultValue = "insertedDateTime") String orderBy) {
		
		try {
			FarmResult result = animalsBL.getAllCows(page, size, orderBy);
	
			if(result.isSucceeded()) {
				return new ResponseEntity<FarmResult>(result, HttpStatus.OK);			
			}	
			
			return new ResponseEntity<FarmResult>(result, HttpStatus.BAD_REQUEST);	
		} catch (Exception e) {
			return new ResponseEntity<FarmResult>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
 







