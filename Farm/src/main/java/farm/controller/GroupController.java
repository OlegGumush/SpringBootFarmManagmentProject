package farm.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import farm.bl.GroupBL;
import farm.entity.group.Group;
import farm.request_model.group.GroupModel;
import farm.response_model.group.GroupResponseModel;
import farm.result.FarmResult;
import io.swagger.annotations.ApiOperation;

@RestController
@Transactional
@CrossOrigin
public class GroupController {

	@Autowired
	private GroupBL groupBL;	
	
	@RequestMapping(value = "/groups", method = RequestMethod.POST)
	@ApiOperation(value = "Create group", notes="notes", nickname = "CreateGroup")
	public ResponseEntity<FarmResult<GroupResponseModel>> createGroup(@RequestBody GroupModel groupModel) {
		
		try {
			FarmResult<Group> result = groupBL.createGroup(groupModel);
			
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.CREATED);
			}
			
			return new ResponseEntity<>(new FarmResult<>(new GroupResponseModel(result.getResult())), HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(value = "/groups/{id}", method = RequestMethod.PUT)
	@ApiOperation(value = "Update group", notes="notes", nickname = "UpdateGroup")
	public ResponseEntity<FarmResult<GroupResponseModel>> updateGroup(@RequestBody GroupModel groupModel, @PathVariable long id) {
		
		try {
			FarmResult<Group> result = groupBL.updateGroup(groupModel, id);
			
			if (!result.isSucceeded()) {
				return new ResponseEntity<>(new FarmResult<>(result.getErrors()), HttpStatus.BAD_REQUEST);			
			}
			
			return new ResponseEntity<>(new FarmResult<>(new GroupResponseModel(result.getResult())), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
