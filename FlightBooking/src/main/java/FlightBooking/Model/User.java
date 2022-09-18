package FlightBooking.Model;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {

	@Id
	private String id;
	@NotBlank
	@Size(max = 20)
	private String firstName;
	@NotBlank
	@Size(max = 20)
	private String lastName;
	@NotBlank
	@Size(max = 20)
	private String username;
	@NotBlank
	@Size(max = 20)
	private String password;
	@NotBlank
	private String gender;
	@Email
	private String email;
	@NotBlank
	@Pattern(regexp = "(^$|[0-9]{10})")
	private String phoneNo;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String firstName, String lastName, String username, String password, String gender, String email,
			String phoneNo) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.email = email;
		this.phoneNo = phoneNo;
	}

	public User(String id, String firstName, String lastName, String username, String password, String gender,
			String email, String phoneNo) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.password = password;
		this.gender = gender;
		this.email = email;
		this.phoneNo = phoneNo;
	}
	public User(String user, String pass) {
		// TODO Auto-generated constructor stub
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

}
