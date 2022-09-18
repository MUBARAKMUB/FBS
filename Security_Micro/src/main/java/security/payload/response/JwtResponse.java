package security.payload.response;

import java.util.List;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private String id;
	private String firstName;
	private String lastname;
	private String username;
	private String email;
	private String phoneNo;
	private String gender;
	private List<String> roles;

	public JwtResponse(String token, String id, String firstName, String lastname, String username, String email,
			String phoneNo, String gender, List<String> roles) {
		super();
		this.token = token;
		this.id = id;
		this.firstName = firstName;
		this.lastname = lastname;
		this.username = username;
		this.email = email;
		this.phoneNo = phoneNo;
		this.gender = gender;
		this.roles = roles;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

}
