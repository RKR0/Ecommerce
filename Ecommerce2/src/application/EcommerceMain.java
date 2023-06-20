package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class EcommerceMain extends Application {
	
	// Initialize UserInterface class
		UserInterface1 UI = new UserInterface1(); 
		
		
		@Override
		public void start(Stage stage) {
			
			Scene scene = new Scene(UI.createContent());
			stage.setTitle("E-Commerce");
			stage.setScene(scene);
			stage.show();
	}
	
	public static void main(String[] args) {
		launch();
	}
}
