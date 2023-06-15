package metier;

public class user {
	private int idUser;
	private String username;
	private String email;
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getIdUser() {
		return idUser;
	}
	public user(int idUser, String username, String password,String email) {
		super();
		this.idUser = idUser;
		this.username = username;
		
		this.email=email;
		
	}
	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
	
	public user(int idUser, String username, String email) {
		super();
		this.idUser = idUser;
		this.username = username;
		this.email = email;
	}
	@Override
	public String toString() {
		return "user [idUser=" + idUser + ", username=" + username + ", email=" + email + "]";
	}


	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
}

						


