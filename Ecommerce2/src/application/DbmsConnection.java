package application;

import java.sql.*;

public class DbmsConnection {
	// Initialize MySql workbench details
private final String dbmsurl = "jdbc:mysql://localhost:3306/ecommerce";
private final String dbmsUsername = "root";
private final String dbmsPassword = "123qaz";

private Statement getStatement() {
	try {
		// connection to DBMS
		Connection connection = DriverManager.getConnection(dbmsurl,dbmsUsername,dbmsPassword);
			return connection.createStatement();
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
	
}

// get data from database
public ResultSet getQuertTable(String query) {
	try {
		Statement statement = getStatement();
			return statement.executeQuery(query);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return null;
	
}

// update data to database
public int updateDatabase(String query) {
	try {
		Statement statement = getStatement();
			return statement.executeUpdate(query);
	}
	catch(Exception e){
		e.printStackTrace();
	}
	return 0;
	
}
public static void main(String args) {
	DbmsConnection dbcon = new DbmsConnection();
	ResultSet rs = dbcon.getQuertTable("select * from customer_details");
	if(rs!=null) {
		System.out.println("sucessful");
	}
	else {
		System.out.println("unsucessful");
	}
}
} 


