package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class ProductList {

	// Initialize table view
	private TableView<Products> productTable;
	
	private TableView<Products> cartTable;
	
	private TableView<Products> searchTable;
	
	// it create table 
	public VBox createTable(ObservableList<Products> data) {
		
		// Initialize the column details
		TableColumn id = new TableColumn("ID");
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn name = new TableColumn("NAME");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn price = new TableColumn("PRICE");
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		// add columns to table and add  complete data
		productTable = new TableView<>();
		productTable.getColumns().addAll(id,name,price);
		productTable.setItems(data);
		productTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		// add table to vbox 
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.getChildren().addAll(productTable);
		return vBox;
	}
	
	// it create products items table
	public VBox getAllProducts() {

		ObservableList<Products> data = Products.getAllProducts();
		return createTable(data);
	}
	
	// check the item is selected or not
	public Products getSelectedProducts(char s) {
		if(s=='p')
			return productTable.getSelectionModel().getSelectedItem();
		if(s=='c')
			return cartTable.getSelectionModel().getSelectedItem();
		if(s=='l')
			return searchTable.getSelectionModel().getSelectedItem();
		
		return null;
	}
	
	// it create a cart items table
	public VBox getItemsInCart(ObservableList<Products> data) {

		// Initialize the column details
				TableColumn id = new TableColumn("ID");
				id.setCellValueFactory(new PropertyValueFactory<>("id"));
				
				TableColumn name = new TableColumn("NAME");
				name.setCellValueFactory(new PropertyValueFactory<>("name"));
				
				TableColumn price = new TableColumn("PRICE");
				price.setCellValueFactory(new PropertyValueFactory<>("price"));
				
				// add columns to table and add  complete data
				cartTable = new TableView<>();
				cartTable.getColumns().addAll(id,name,price);
				cartTable.setItems(data);
				cartTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				
				// add table to vbox 
				VBox vBox = new VBox();
				vBox.setPadding(new Insets(10));
				vBox.getChildren().addAll(cartTable);
				return vBox;
	}
	
	public VBox getsearchProducts(String str) {
		
		ObservableList<Products> data = Products.getAllProducts(str);
		TableColumn id = new TableColumn("ID");
		id.setCellValueFactory(new PropertyValueFactory<>("id"));
		
		TableColumn name = new TableColumn("NAME");
		name.setCellValueFactory(new PropertyValueFactory<>("name"));
		
		TableColumn price = new TableColumn("PRICE");
		price.setCellValueFactory(new PropertyValueFactory<>("price"));
		
		// add columns to table and add  complete data
		searchTable = new TableView<>();
		searchTable.getColumns().addAll(id,name,price);
		searchTable.setItems(data);
		searchTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		
		// add table to vbox 
		VBox vBox = new VBox();
		vBox.setPadding(new Insets(10));
		vBox.getChildren().addAll(searchTable);
		return vBox;
	}
	
}
