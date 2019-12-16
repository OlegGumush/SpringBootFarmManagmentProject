package farm.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.AnimalBL;
import farm.entity.animal.Bull;
import farm.request_model.animal.CerateBullModel;
import farm.request_model.animal.UpdateBullModel;
import farm.response_model.animal.BullResponseModel;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
@CrossOrigin
public class BullController {

	@Autowired
	private AnimalBL animalsBL;
	
	@RequestMapping(value = "/bulls", method = RequestMethod.POST)
	@ApiOperation(value = "Create bull", notes="notes", nickname = "CreateBull")
	public ResponseEntity<FarmResult<BullResponseModel>> createBull(@RequestBody CerateBullModel bullModel) {
		
		try {
			FarmResult<Bull> result = animalsBL.createBull(bullModel);
			
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<>(new FarmResult<>(new BullResponseModel(result.getResult())), HttpStatus.CREATED);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/bulls/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update bull", notes="notes", nickname = "UpdateBull")
	public ResponseEntity<FarmResult<BullResponseModel>> updateBull(@RequestBody UpdateBullModel bullModel, @PathVariable long id) {
		
		try {
			FarmResult<Bull> result = animalsBL.updateBull(bullModel, id);
			
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);			
			}
			
			return new ResponseEntity<>(new FarmResult<>(new BullResponseModel(result.getResult())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/bulls", params = { "page", "size", "sort" }, method = RequestMethod.GET)
	@ApiOperation(value = "Get all bulls", notes="notes", nickname = "GetAllBulls")
	public ResponseEntity<FarmResult<List<BullResponseModel>>> getAllBulls(@RequestParam(name = "page", defaultValue = "0") int page,
		    								      @RequestParam(name = "size", defaultValue = "100") int size,
												  @RequestParam(name = "sort", defaultValue = "insertedDateTime") String orderBy) {
		try {
			FarmResult<List<Bull>> result = animalsBL.getAllBulls(page, size, orderBy);
	
			if(!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);
			}	
			
			List<BullResponseModel> bulls = result.getResult().stream().map(BullResponseModel::new).collect(Collectors.toList());
			
			return new ResponseEntity<>(new FarmResult<>(bulls), HttpStatus.OK);			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}













