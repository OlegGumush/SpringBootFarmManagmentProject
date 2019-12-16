package farm.request_model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class UserModel {

    @ApiModelProperty(required = true)
	public String Username;
    
    @ApiModelProperty(required = true)
    public String Password;
    
    @ApiModelProperty(required = true)
    public String Name;
    
    @ApiModelProperty(required = true)
    public String Email;
}
