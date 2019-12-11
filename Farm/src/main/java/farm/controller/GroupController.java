package farm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.GroupBL;
import farm.model.group.GroupModel;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
public class GroupController {

	@Autowired
	private GroupBL groupBL;	
	
	@RequestMapping(value = "/groups", method = RequestMethod.POST)
	@ApiOperation(value = "Create group", notes="notes", nickname = "CreateGroup")
	public ResponseEntity<FarmResult> createGroup(@RequestBody GroupModel groupModel) {
		
		try {
			FarmResult result = groupBL.createGroup(groupModel);
			
			if (result.isSucceeded()) {
				return new ResponseEntity<FarmResult>(result, HttpStatus.CREATED);
			}
			
			return new ResponseEntity<FarmResult>(result, HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			return new ResponseEntity<FarmResult>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
