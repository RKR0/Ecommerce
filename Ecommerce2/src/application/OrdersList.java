package application;

import java.sql.Date;
import java.sql.ResultSet;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;



public class OrdersList {

	public class Items{
		private int  order_id;
		private String name;
		private double price;
		private Date order_date;
		public Items(int order_id, String name, double price, Date order_date) {
			super();
			this.order_id = order_id;
			this.name = name;
			this.price = price;
			this.order_date = order_date;
		}
		
		
		public int getOrder_id() {
			return order_id;
		}



		public String getName() {
			return name;
		}
		public double getPrice() {
			return price;
		}
		
		public Date getOrder_date() {
			return order_date;
		}
	}
	
	private TableView<Items> orderTable;
	
	@SuppressWarnings("unchecked")
	public VBox createTable1(ObservableList<Items> data) {
		
		
		
		TableColumn orderid = new TableColumn("Order Id");
		orderid.setCellValueFactory(new PropertyValueFactory<>("order_id"));
		
		TableColumn name = new TableColumn("Product Name");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn price = new TableColumn("Price");
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		TableColumn date = new TableColumn("Order Date");
		date.setCellValueFactory(new PropertyValueFactory<>("order_date"));
		
		
		orderTable = new TableView<>();
		orderTable.getColumns().addAll(orderid,name,price,date);
		orderTable.setItems(data);
		orderTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.getChildren().addAll(orderTable);
		return vBox;
	}
	
	
	public VBox getAllProducts(int customerid) {
	
		ObservableList<Items> data = fetchProductsdata(customerid);
		return createTable1(data);
	}
	
	public ObservableList<Items> fetchProductsdata(int customerid){
		
		ObservableList<Items> data = FXCollections.observableArrayList();
		DbmsConnection dbcon = new DbmsConnection();
		String query = "select order_id,name,price,order_date from orders as o join product_details as p on p.id=o.product_id where o.customer_id = "+customerid;
		
		try {
			ResultSet rs = dbcon.getQuertTable(query);
			while(rs.next()) {
				Items item = new Items(rs.getInt("order_id"),rs.getString("name"),rs.getDouble("price"),rs.getDate("order_date"));
				data.add(item);
			}
			return data;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
}
