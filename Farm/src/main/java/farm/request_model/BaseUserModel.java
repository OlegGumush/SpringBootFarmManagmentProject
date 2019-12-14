package farm.request_model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class BaseUserModel {

    @ApiModelProperty(required = true)
	public String Username;
    
    @ApiModelProperty(required = true)
    public String Password;
    
    @ApiModelProperty(required = true)
    public String Name;
    
    @ApiModelProperty(required = true)
    public String Email;
}
