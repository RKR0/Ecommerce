package application;

import java.util.stream.Stream;

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
		userde();
		profile();
		
	}
	
	ProductList productList = new ProductList();
	OrdersList orderList = new OrdersList();

	GridPane loginPage; 
	GridPane regPage;
	HBox headerBar;
	HBox FooterBar;
	VBox productPage;
	VBox body;
	Button signInButton;
	Customers loggedIncustmoer;
	Label welcomeLabel;
	VBox orderPage;
	GridPane userPage;
	Button profileButton = new Button("Profile");

	
	ObservableList<Products> ItemsInCart = FXCollections.observableArrayList();
	
	
	BorderPane createContent() {
		BorderPane root = new BorderPane();
		root.setPrefSize(750, 500);
		root.setTop(headerBar);
		root.setBottom(FooterBar);
		body = new VBox();
		body.setPadding(new Insets(10));
		body.setAlignment(Pos.CENTER);
		root.setCenter(body); 
		productPage = productList.getAllProducts();
		body.getChildren().add(productPage);

		
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
	Button skipButton = new Button("Skip");
	Button adminButton = new Button("Admin Login");
	Button regButton = new Button("New User");
	
	loginPage = new GridPane();
	loginPage.setAlignment(Pos.CENTER);
	loginPage.setHgap(5);
	loginPage.setVgap(5);
	loginPage.add(userNameText,0,0);
	loginPage.add(passwordText,0,1);
	loginPage.add(user,1,0);
	loginPage.add(password,1,1);
	loginPage.add(loginButton, 1, 2);
	loginPage.add(skipButton, 0, 2);
	loginPage.add(regButton, 0, 3);
	
	// logIn button
	loginButton.setOnAction(new EventHandler<ActionEvent>(){


		@Override
		public void handle(ActionEvent arg0) {
			// get text from userName and password
			String username = user.getText();
			String pass = password.getText();
			Login login = new Login();
		
			String encpass = login.encrypt(pass);
			//System.out.println(encpass);
			
			
			
			// pass userName and password login
			loggedIncustmoer = login.customerLogin(username, encpass);
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
	
	skipButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		// clear all and add main product page visible FooterBar
		body.getChildren().clear();
		body.getChildren().add(productPage);
		headerBar.getChildren().add(signInButton);
		headerBar.setVisible(true);
		FooterBar.setVisible(true);
			
		}
		
	});


	
	regButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		// clear all and add main product page visible FooterBar
		body.getChildren().clear();
		body.getChildren().add(regPage);
		
		headerBar.setVisible(false);
		FooterBar.setVisible(false);
			
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
	
	
	
	
	 //Initialize image to home button
	Image image = new Image("C:\\Users\\ramak\\OneDrive\\Documents\\Accio projects\\Ecommerce2\\src\\application\\Untitled.png");
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
	headerBar.getChildren().addAll(homeButton,searchBar,searchButton,cartButton,orderButton,profileButton,signInButton);
	
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
			Products product = productList.getSelectedProducts('c');
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
		
		for(Products product:ItemsInCart) {
			if(product.getQuantity()==0) {
				showDialog("Sorry!! This "+product.getName()+" is out of Stack");
				return;
			}
		}
		
		
		// pass cartItems and customer details to order multiple items
		int status = Order.placeMultipleOrders(loggedIncustmoer, ItemsInCart);
		if(status!=0) {
			showDialog("Order Placed "+status+" Products Sucessfully!!");
			ItemsInCart.clear();
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

	searchButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		// clear the body and remove headerBar and FooterBar
		body.getChildren().clear();
		
		VBox selectedproducts =  productList.getsearchProducts(searchBar.getText());
		

		
		//  
		body.getChildren().add(selectedproducts);
		

		}
		
	});
	
	
	



	

	
	
	

}


// FooterBar
public void createFooterBar() {

	// initialize Buttons
	Button buyNowButton = new Button("Buy Now");
	Button addtoCartButton = new Button("Add to Cart");
	Button signOutButton = new Button("Sign Out");
	
	// initialize HBox and add buttons to FooterBar
	FooterBar  = new HBox();
	FooterBar.setAlignment(Pos.CENTER);
	FooterBar.setSpacing(10);
	FooterBar.setPadding(new Insets(15));
	FooterBar.getChildren().addAll(buyNowButton,addtoCartButton,signOutButton);
	
	// Buy button
	buyNowButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			
			
		Products product = productList.getSelectedProducts('p');
		
		if(body.getChildren().contains(productPage)==false)
			 product = productList.getSelectedProducts('l');
		
			
		
		
		// check user logged in or not
		if(loggedIncustmoer==null) {
			showDialog("Please LogIn first to Place a Order!");
			return;
		}
		
		
		if(product.getQuantity()==0) {
			showDialog("Sorry!! This "+product.getName()+" is out of Stack");
			return;
		}
		
		
		
		// pass the customer and product to place a order
		boolean status = Order.placeOrder(loggedIncustmoer, product);
		if(status==true) {
			Products.updateQuantity(product);
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
			Products product = productList.getSelectedProducts('p');
			

			if(body.getChildren().contains(productPage)==false){
				product = productList.getSelectedProducts('l');
			}
			 	
			
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
	
	
	signOutButton.setOnAction((EventHandler<ActionEvent>) new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		// clear the body and remove headerBar and FooterBar
			
			if(loggedIncustmoer!=null) {
			loggedIncustmoer = null;
			body.getChildren().clear();
			body.getChildren().add(productPage);
			headerBar.getChildren().remove(welcomeLabel);
			headerBar.getChildren().add(signInButton);
			headerBar.setVisible(true);
			FooterBar.setVisible(true);
			ItemsInCart.clear();
			}
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

public void profile() {
	
	
	Text name = new Text("Name");
	Text mobile = new Text("Mobile");
	Text email = new Text("Email");
	Text Adress = new Text("Adress");
	Text userName = new Text("User Name");
	Text password = new Text("Enter new Password");
	
	Label message1 = new Label();
	TextField name1 = new TextField();
	TextField mobile1 = new TextField();
	TextField email1 = new TextField();
	TextField Adress1 = new TextField();
	TextField userName1 = new TextField();
	TextField password1 = new TextField();
	
	Button updateButton = new Button("Update");
	
	userPage = new GridPane();
	userPage.setAlignment(Pos.CENTER);
	userPage.setHgap(5);
	userPage.setVgap(5);
	
	
	
	userName1.setEditable(false);
	
	userPage.add(name,0,0);
	userPage.add(mobile,0,1);
	userPage.add(email,0,2);
	userPage.add(Adress,0,3);
	userPage.add(userName,0,4);
	userPage.add(password,0,5);
	
	userPage.add(name1,1,0);
	userPage.add(mobile1,1,1);
	userPage.add(email1,1,2);
	userPage.add(Adress1,1,3);
	userPage.add(userName1,1,4);
	userPage.add(password1,1,5);
	
	message1.setText("Password Change is not Mandetory!!");
	userPage.add(message1, 2, 5);
	
	
	userPage.add(updateButton,1,6);
	
	profileButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		
		// check customer loggedIn or not
		if(loggedIncustmoer==null) {
			showDialog("Please LogIn !");
				return;
			}

		// clear the body and add orderPage 
		body.getChildren().clear();
		body.getChildren().add(userPage);
		// remove footerBar
		FooterBar.setVisible(false);
		name1.setText(loggedIncustmoer.getName());
		mobile1.setText(loggedIncustmoer.getMobile());
		email1.setText(loggedIncustmoer.getEmail());
		Adress1.setText(loggedIncustmoer.getAddress());
		userName1.setText(loggedIncustmoer.getUserName());
		}
	
	});
	
	updateButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
			Login login = new Login();
			String name2 = name1.getText();
			String mobile2 = mobile1.getText();
			String email2 = email1.getText();
			String Adress2 = Adress1.getText();
			String password2 = loggedIncustmoer.getPassword();
		if(!password1.getText().isEmpty()) {
			
			password2 = login.encrypt(password1.getText());
		}
		
		
		
		if(name2.isEmpty() || mobile2.isEmpty() || Adress2.isEmpty()|| password2.isEmpty()) {
			showDialog("Please Fill the all Details!!");
			return;
			
		}
		if(!name2.matches("[a-zA-Z]*$")) {
			showDialog("Please enter valid Name");
			return;	
		}
		if(!email2.contains("@")) {
			showDialog("Please enter valid email");
			return;
			
		}
		else if(mobile2.length()!=10 || !mobile2.matches("[0-9]*$") || !Stream.of("6", "7","8","9").anyMatch(mobile2::startsWith) ) {
			showDialog("Please enter valid mobile Number");
			return;
			
		}
			
		 
			
			
			
			String updateuserq = "update customer_details set Name = '"+name2+"',mobile='"+mobile2+"', email = '"+email2+"',Address = '"+Adress2+"',password = '"+password2+"' where customer_id = "+loggedIncustmoer.getId();
			// connect with database
			DbmsConnection dbcon = new DbmsConnection();
			
			if(dbcon.updateDatabase(updateuserq)!=0) {
				showDialog("User Deatails updated Sucessfully.");	
				loggedIncustmoer = login.customer1(loggedIncustmoer.getId());
				welcomeLabel.setText("Hey! "+loggedIncustmoer.getName());
				
			}
			else {
				showDialog("something went wrong");	
				return;
			}

		// clear the body and add orderPage 
		body.getChildren().clear();
		body.getChildren().add(userPage);
		// remove footerBar
		FooterBar.setVisible(false);
		name1.setText(loggedIncustmoer.getName());
		mobile1.setText(loggedIncustmoer.getMobile());
		email1.setText(loggedIncustmoer.getEmail());
		Adress1.setText(loggedIncustmoer.getAddress());
		userName1.setText(loggedIncustmoer.getUserName());
		}
	
	});
	
	


}

public void userde() {
	
	Text name = new Text("Name");
	Text mobile = new Text("Mobile");
	Text email = new Text("Email");
	Text Adress = new Text("Adress");
	Text userName = new Text("User Name");
	Text password = new Text("Password");
	
	
	
	TextField name1 = new TextField();
	TextField mobile1 = new TextField();
	TextField email1 = new TextField();
	TextField Adress1 = new TextField();
	TextField userName1 = new TextField();
	TextField password1 = new TextField();
	
	
	Button adduserButton = new Button("Add"); 
	Label message = new Label();
	Button cancelButton = new Button("cancel");
	
	
	regPage = new GridPane();
	regPage.setAlignment(Pos.CENTER);
	regPage.setHgap(5);
	regPage.setVgap(5);
	
	
	regPage.add(name,0,0);
	regPage.add(mobile,0,1);
	regPage.add(email,0,2);
	regPage.add(Adress,0,3);
	regPage.add(userName,0,4);
	regPage.add(password,0,5);
	
	regPage.add(name1,1,0);
	regPage.add(mobile1,1,1);
	regPage.add(email1,1,2);
	regPage.add(Adress1,1,3);
	regPage.add(userName1,1,4);
	regPage.add(password1,1,5);
	
	regPage.add(adduserButton,0,6);
	regPage.add(cancelButton,1,6);
	
	
	cancelButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {
		// clear all and add main product page visible FooterBar
		body.getChildren().clear();
		body.getChildren().add(productPage);
		headerBar.getChildren().add(signInButton);
		headerBar.setVisible(true);
		FooterBar.setVisible(true);
			
		}
		
	});
	
	
	adduserButton.setOnAction(new EventHandler<ActionEvent>() {

		@Override
		public void handle(ActionEvent arg0) {

		
		
		String name2 = name1.getText();
		String mobile2 = mobile1.getText();
		String email2 = email1.getText();
		String Adress2 = Adress1.getText();
		String userName2 = userName1.getText();
		String password2 = password1.getText();
		
		Login login = new Login();
		password2 = login.encrypt(password1.getText());
		
		
		if(name2.isEmpty() || mobile2.isEmpty() || Adress2.isEmpty() || userName2.isEmpty() || password2.isEmpty()) {
			showDialog("Please Fill the all Details!!");
			
		}
		else if(!name2.matches("[a-zA-Z]*$")) {
			showDialog("Please enter valid Name");
			
		}
		else if(!email2.contains("@")) {
			showDialog("Please enter valid email");
			
		}
		else if(mobile2.length()!=10 || !mobile2.matches("[0-9]*$") || !Stream.of("6", "7","8","9").anyMatch(mobile2::startsWith) ) {
			showDialog("Please enter valid mobile Number");
			
		}
		else {
			password2 = login.encrypt(password1.getText());
			String adduserq = "Insert into customer_details(Name,mobile,email,Address,user_name,password) values ('"+name2+"','"+mobile2+"','"+email2+"','"+Adress2+"','"+userName2+"','"+password2+"')";
			// connect with database
			DbmsConnection dbcon = new DbmsConnection();
			if(dbcon.updateDatabase(adduserq)!=0) {
				showDialog("User Deatails Added Sucessfully.");	
				body.getChildren().clear();
				body.getChildren().add(productPage);
				headerBar.getChildren().add(signInButton);
				headerBar.setVisible(true);
				FooterBar.setVisible(true);
			}
			else {
				showDialog("User Name is Already Exist Please enter the uniqe user Name.");	
			}
			
		}
			
		}
		
	});
	

	
	
}

	
}
