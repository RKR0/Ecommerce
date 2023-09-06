package application;

import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Products {

	private int id;
	private String name;
	private double price;
	private int quantity;
	
	
	

	public Products(int id, String name, double price,int quantity) { 
  		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}
	
	public static ObservableList<Products> getAllProducts(){
		
		String selectAllProducts = "select * from product_details";
		return fetchProductsdata(selectAllProducts);
		
		
	}
	
		public static ObservableList<Products> getAllProducts(String str){
		
		String selectAllProducts = "select * from product_details where name like '%"+str+"%'";
		return fetchProductsdata(selectAllProducts);
		
		
	}
	
	
	// get products from product table in database 
	public static ObservableList<Products> fetchProductsdata(String query){
		// store the all product details from database into 'data'
		ObservableList<Products> data = FXCollections.observableArrayList();
		DbmsConnection dbcon = new DbmsConnection();
		
		try {
			ResultSet rs = dbcon.getQuertTable(query);
			while(rs.next()) {
				Products product = new Products(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"),rs.getInt("quantity"));
				data.add(product);
			}
			return data;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public static void updateQuantity(Products product) {
			DbmsConnection dbcon = new DbmsConnection();
			
			String queryUpdate = "UPDATE product_details SET quantity = "+(product.getQuantity()-1)+" WHERE id = "+product.getId()+"";
			dbcon.updateDatabase(queryUpdate);
		
}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public double getPrice() {
		return price;
	}
	public int getQuantity() {
		return quantity;
	}
	
	
	
	
	
	
}
