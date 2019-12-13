package farm.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import farm.entity.animal.Cow;
import farm.request_model.create_animal.CreateCowModel;
import farm.request_model.update_animal.UpdateCowModel;
import farm.response_model.animal.CowResponseModel;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
public class CowController {

	@Autowired
	private AnimalBL animalsBL;
	
	@RequestMapping(value = "/cows", method = RequestMethod.POST)
	@ApiOperation(value = "Create cow", notes="notes", nickname = "CreateCow")
	public ResponseEntity<FarmResult<CowResponseModel>> createCow(@RequestBody CreateCowModel cowModel) {
		
		try {
			FarmResult<Cow> result = animalsBL.createCow(cowModel);
			 
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);
			}
	
			return new ResponseEntity<>(new FarmResult<>(new CowResponseModel(result.getResult())), HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/cows/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update cow", notes="notes", nickname = "UpdateCow")
	public ResponseEntity<FarmResult<CowResponseModel>> updateCow(@RequestBody UpdateCowModel cowModel, @PathVariable long id) {
		
		try {
			FarmResult<Cow> result = animalsBL.updateCow(cowModel, id);
			
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(new FarmResult<>(new CowResponseModel(result.getResult())), HttpStatus.OK);
		
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/cows", params = { "page", "size", "sort" }, method = RequestMethod.GET)
	@ApiOperation(value = "Get all cows", notes="notes", nickname = "GetAllCows")
	public ResponseEntity<FarmResult<List<CowResponseModel>>> getAllCows(@RequestParam(name = "page", defaultValue = "0") int page,
		      									 @RequestParam(name = "size", defaultValue = "100") int size,
												 @RequestParam(name = "sort", defaultValue = "insertedDateTime") String orderBy) {
		try {
			FarmResult<List<Cow>> result = animalsBL.getAllCows(page, size, orderBy);
	
			if(!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);	
			}	
			
			List<CowResponseModel> bulls = result.getResult().stream().map(CowResponseModel::new).collect(Collectors.toList());
			return new ResponseEntity<>(new FarmResult<>(bulls), HttpStatus.OK);	
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
 







