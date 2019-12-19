package farm.request_model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseUserModel {

    @ApiModelProperty(required = true, allowableValues = "range[1, 20]" )
	public String Username;
    
    @ApiModelProperty(required = true, allowableValues = "range[1, 20]")
    public String Name;
    
    @ApiModelProperty(required = true, allowableValues = "range[1, 50]")
    public String Email;
}
