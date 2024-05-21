package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application {
	private GUIManager guiManager;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			guiManager = new GUIManager();
			guiManager.setGUI();
			guiManager.setButtonEvent();
			
			Scene scene = new Scene(guiManager.getRoot());
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Calculator");
			primaryStage.setScene(scene);
			primaryStage.show();
		} 
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
