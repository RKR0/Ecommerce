package application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.image.*;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;


public class UserInterface1 {
	
	UserInterface1(){

		createLoginPage(); 
		createHeaderBar();
		createFooterBar();
		
	}
	
	ProductList productList = new ProductList();
	OrdersList orderList = new OrdersList();

	GridPane loginPage; 
	HBox headerBar;
	HBox FooterBar;
	VBox productPage;
	VBox body;
	Button signInButton;
	Customers loggedIncustmoer;
	Label welcomeLabel;
	VBox orderPage;
	
	ObservableList<Products> ItemsInCart = FXCollections.observableArrayList();
	
	
	BorderPane createContent() {
		BorderPane root = new BorderPane();
		root.setPrefSize(750, 500);
		//root.getChildren().add(loginPage);
		root.setTop(headerBar);
		root.setBottom(FooterBar);
		//root.setCenter(loginPage); 
		body = new VBox();
		body.setPadding(new Insets(10));
		body.setAlignment(Pos.CENTER);
		root.setCenter(body); 
		productPage = productList.getAllProducts();
		body.getChildren().add(productPage);
		
		//root.setCenter(productPage); 
		
		return root;
	}
	
	
	
public void createLoginPage() {
	Text userNameText = new Text("User Name");
	Text passwordText = new Text("Password");
	
	TextField user = new TextField();
	user.setPromptText("Type your User Name here");
	PasswordField password = new PasswordField();
	password.setPromptText("Type your Password here");
	
	Button loginButton = new Button("Login"); 
	Label message = new Label();
	
	loginPage = new GridPane();
	loginPage.setAlignment(Pos.CENTER);
	loginPage.setHgap(5);
	loginPage.setVgap(5);
	loginPage.add(userNameText,0,0);
	loginPage.add(passwordText,0,1);
	loginPage.add(user,1,0);
	loginPage.add(password,1,1);
	loginPage.add(loginButton, 1, 2);
	
	// logIn button
	loginButton.setOnAction(new EventHandler<ActionEvent>(){


		@Override
		public void handle(ActionEvent arg0) {
			// get text from userName and password
			String username = user.getText();
			String pass = password.getText();
			Login login = new Login();
			// pass userName and password login
			loggedIncustmoer = login.customerLogin(username, pass);
			if(loggedIncustmoer!=null) {
				message.setText("Welcome"+loggedIncustmoer.getName());
				welcomeLabel.setText("Hey! "+loggedIncustmoer.getName());
				headerBar.getChildren().add(welcomeLabel);
				body.getChildren().clear();
				body.getChildren().add(productPage);
				headerBar.setVisible(true);
				FooterBar.setVisible(true);
			}
			else {
				message.setText("LogIn Failed !! Please enter valid User Name And Password");
				loginPage.add(message, 1, 3);
			}
			
			
		}
		
	});
	
	

}
	
// HeaderBar 
public void createHeaderBar() {
	// initialize searchBar 
	TextField searchBar = new TextField();
	searchBar.setPromptText("Search here");
	searchBar.setPrefWidth(250);
	
	// initialize headerBar buttons
	Button homeButton = new Button();
	Button searchButton = new Button("Search");
			signInButton = new Button("Sign In");
	Button cartButton = new Button("Cart");
	Button orderButton = new Button("Orders");
	Button placeOrder = new Button("Place Oreder");
	Button removeButton = new Button("Remove");
	
	// Initialize image to home button
	Image image = new Image("C:/Users/ramak/eclipse-workspace/Ecommerce2/src/application/Untitled.png");
	ImageView imageView = new ImageView();
	imageView.setImage(image);
	imageView.setFitHeight(20);
	imageView.setFitWidth(100);
	homeButton.setGraphic(imageView);

	
	welcomeLabel = new Label(); 
	
	// initialize headerBar and add Buttons
	headerBar  = new HBox();
	headerBar.setAlignment(Pos.CENTER);
	headerBar.setSpacing(5);
	headerBar.setPadding(new Insets(15));
	headerBar.getChildren().addAll(homeButton,searchBar,searchButton,signInButton,cartButton,orderButton);
	
	// signIn button
	signInButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		// clear the body and remove headerBar and FooterBar
		body.getChildren().clear();
		headerBar.setVisible(false);
		FooterBar.setVisible(false);
		// add login page 
		body.getChildren().add(loginPage);
		// after successful login remove signIn button from headerBar  
		headerBar.getChildren().remove(signInButton);
		
		}
		
	});

	// cart Button
	cartButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		// clear the body and 
		body.getChildren().clear();
		VBox cartPage = productList.getItemsInCart(ItemsInCart);
		cartPage.setAlignment(Pos.CENTER);
		cartPage.setSpacing(10);
		//cartPage.getChildren().addAll(placeOrder,removeButton);
		
		// create new footer bar and buttons
		HBox cartFooterBar  = new HBox();
		cartFooterBar.setAlignment(Pos.CENTER);
		cartFooterBar.setSpacing(10);
		cartFooterBar.setPadding(new Insets(15));
		cartFooterBar.getChildren().addAll(placeOrder,removeButton);
		
		// add cart page and new footerBar to body
		body.getChildren().addAll(cartPage,cartFooterBar);
		// remove main page footBar 
		FooterBar.setVisible(false);
		
		
		
			
		}
		
	});

	// remove button
	removeButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			Products product = productList.getSelectedProducts();
			// check item is selected or not
			if(product==null) {
				showDialog("Please Select Product!");
				return;
			}
			// check user logged in or not
			if(loggedIncustmoer==null) {
				showDialog("Please LogIn!");
				return;
			}
			// remove product from cart
			ItemsInCart.remove(product);
			showDialog("Product has been removed to Cart Sucessfully.");
		}
		
	});

	// place order button
	placeOrder.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {

		// check cart is empty
		if(ItemsInCart.isEmpty()) {
			showDialog("Please add Products to Place a Order!");
			return;
		}
		// check user logged in or not
		if(loggedIncustmoer==null) {
			showDialog("Please LogIn first to Place a Order!");
			return;
		}
		// pass cartItems and customer details to order multiple items
		int status = Order.placeMultipleOrders(loggedIncustmoer, ItemsInCart);
		if(status!=0) {
			showDialog("Order Placed"+status+" Products Sucessfully!!");
		}
		else {
			showDialog("Order Failed!");
		}
		
		}
		
	});

	//Home button
	homeButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		// clear all and add main product page visible FooterBar
		body.getChildren().clear();
		body.getChildren().add(productPage);
		FooterBar.setVisible(true);
			
		}
		
	});

	// orders button
	orderButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		
		// check customer loggedIn or not
		if(loggedIncustmoer==null) {
			showDialog("Please LogIn !");
				return;
			}

		// clear the body and add orderPage 
		body.getChildren().clear();
		orderPage = orderList.getAllProducts(loggedIncustmoer.getId());
		body.getChildren().add(orderPage);
		// remove footerBar
		FooterBar.setVisible(false);

			
		}
		
	});

}


// FooterBar
public void createFooterBar() {

	// initialize Buttons
	Button buyNowButton = new Button("Buy Now");
	Button addtoCartButton = new Button("Add to Cart");
	
	// initialize HBox and add buttons to FooterBar
	FooterBar  = new HBox();
	FooterBar.setAlignment(Pos.CENTER);
	FooterBar.setSpacing(10);
	FooterBar.setPadding(new Insets(15));
	FooterBar.getChildren().addAll(buyNowButton,addtoCartButton);
	
	// Buy button
	buyNowButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		Products product = productList.getSelectedProducts();
		
		// check item is selected or not
		if(product==null) {
			showDialog("Please Select Product to Place a Order!");
			return;
		}
		
		// check user logged in or not
		if(loggedIncustmoer==null) {
			showDialog("Please LogIn first to Place a Order!");
			return;
		}
		// pass the customer and product to place a order
		boolean status = Order.placeOrder(loggedIncustmoer, product);
		if(status==true) {
			showDialog("Order Placed Sucessfully!!");
		}
		else {
			showDialog("Order Failed!");
		}
		
		}
		
	});
	
	// // add to cart button
	addtoCartButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			Products product = productList.getSelectedProducts();
			// check item is selected or not
			if(product==null) {
				showDialog("Please Select Product to add it to Cart!");
				return;
			}
			// check user logged in or not
			if(loggedIncustmoer==null) {
				showDialog("Please LogIn first to add it to Cart!");
				return;
			}
			// add product to cart
			ItemsInCart.add(product);
			showDialog("Product has been added to Cart Sucessfully.");
		}
		
	});
	
}


// message to user 
private void showDialog(String msg) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Message");
		alert.setContentText(msg);
		alert.showAndWait();
	}


}