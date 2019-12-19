package farm.request_model.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class LoginModel {

    @ApiModelProperty(required = true, allowableValues = "range[1, 20]")
	public String Username;	
    
    @ApiModelProperty(required = true, allowableValues = "range[6, 50]")
	public String Password;
}
