package application;

import java.sql.ResultSet;

import javafx.collections.ObservableList;

public class Order {
	
	
	// single item purchase
	public static boolean placeOrder(Customers customer,Products product) {
		
		// select last updated group_order_id and give a new id for next order
		String groupOrderId = "SELECT max(group_order_id)+1 id FROM orders";
		// initialize DbmsConnection to connect database
		DbmsConnection dbcon = new DbmsConnection();
		try {
			ResultSet rs = dbcon.getQuertTable(groupOrderId);
			if(rs.next()) {
				// update order details to order table
				String order = "INSERT INTO orders(group_order_id,customer_id,product_id) VALUES("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
				return dbcon.updateDatabase(order)!=0;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	// multiple items purchase
	public static int placeMultipleOrders(Customers customer,ObservableList<Products> ItemsInCart) {
		// select last updated group_order_id and give a new id for next order
		String groupOrderId = "SELECT max(group_order_id)+1 id FROM orders";
		DbmsConnection dbcon = new DbmsConnection();
		try {
			ResultSet rs = dbcon.getQuertTable(groupOrderId);
			// count for no of items
			int count =0;
			if(rs.next()) {
				
				
				for(Products product:ItemsInCart) {
					// update order details to order table
					
					String order = "INSERT INTO orders(group_order_id,customer_id,product_id) VALUES("+rs.getInt("id")+","+customer.getId()+","+product.getId()+")";
					count+=dbcon.updateDatabase(order);
					// update data base productDetails
					Products.updateQuantity(product);
				}
				return count;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
		return 0;
		
	}
	
}
