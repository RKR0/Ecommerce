package application;

import java.sql.ResultSet;

public class Login {

	// login
	public Customers customerLogin(String userName, String password) {
	// search userName and password in customer_details table
	String loginQuery = "select * From customer_details where user_name = '"+userName+"' and password = '"+password+"'";
	// connect with database
	DbmsConnection dbcon = new DbmsConnection();
	
	ResultSet rs = dbcon.getQuertTable(loginQuery);
	try {
		if(rs.next())
			return new Customers(rs.getInt("customer_id"),rs.getString("Name"),rs.getString("email"),rs.getString("mobile"));

	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}

}