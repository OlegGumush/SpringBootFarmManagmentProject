package farm.response_model;

public class JwtResponse {

	public String JWT;
	
	public JwtResponse(String jwt) {
		this.JWT = jwt; 
	}

	public String getJWT() {
		return JWT;
	}

	public void setJWT(String jWT) {
		JWT = jWT;
	}
}
