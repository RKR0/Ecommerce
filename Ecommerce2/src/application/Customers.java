package application;

public class Customers {

	// Initialize customer details 
	private int id;
	private String name,email,mobile;
	
	
	public Customers(int id, String name, String email, String mobile) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
	}


	public int getId() {
		return id;
	}


	public String getName() {
		return name;
	}


	public String getEmail() {
		return email;
	}


	public String getMobile() {
		return mobile;
	}

	
	
	
	
}
