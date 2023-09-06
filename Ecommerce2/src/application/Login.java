
package application;

import java.security.MessageDigest;
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
			return new Customers(rs.getInt("customer_id"),rs.getString("Name"),rs.getString("email"),rs.getString("mobile"),rs.getString("Address"),rs.getString("user_name"),rs.getString("password"));

	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}
	
	public Customers customer1(int id) {
	// search userName and password in customer_details table
	String loginQuery = "select * From customer_details where  customer_id= "+id;
	// connect with database
	DbmsConnection dbcon = new DbmsConnection();
	
	ResultSet rs = dbcon.getQuertTable(loginQuery);
	try {
		if(rs.next())
			return new Customers(rs.getInt("customer_id"),rs.getString("Name"),rs.getString("email"),rs.getString("mobile"),rs.getString("Address"),rs.getString("user_name"),rs.getString("password"));

	}
	catch(Exception e) {
		e.printStackTrace();
	}
	return null;
}

public String encrypt(String msg) {
		
		try {
			MessageDigest messageDigest = MessageDigest.getInstance("SHA");
			messageDigest.update(msg.getBytes());
			byte bytearr[] = messageDigest.digest();
			
			StringBuilder str = new StringBuilder();
			
			for(byte b:bytearr) {
				str.append(String.format("%02x",b));
			}
			return str.toString();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return "";
	}

}
