package farm.request_model.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class CreateUserModel extends BaseUserModel {
    
    @ApiModelProperty(required = true, allowableValues = "range[6, 50]")
    public String Password;
}
