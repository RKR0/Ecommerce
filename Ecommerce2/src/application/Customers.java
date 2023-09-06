package application;

import javafx.scene.layout.GridPane;

public class Customers {

	// Initialize customer details 
	private int id;
	private String name,email,mobile,userName,password,address;
	
	
	public Customers(int id, String name, String email, String mobile,String address,String userName,String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.mobile = mobile;
		this.userName = userName;
		this.password = password;
		this.address =address;
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


	public String getUserName() {
		return userName;
	}


	public String getPassword() {
		return password;
	}


	public String getAddress() {
		return address;
	}



	


	

	
	
	
	
}
