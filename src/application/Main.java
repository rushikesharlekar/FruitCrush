package application;
	
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
	
	private GameMenu gameMenu; 
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = FXMLLoader.load(getClass().getResource("/application/Main.fxml"));
			Scene scene = new Scene(root,800,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			gameMenu = new GameMenu();
//			FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
//            ft.setFromValue(0);
//            ft.setToValue(1);
	        gameMenu.setVisible(true);
//	        ft.play();
	        

	        root.getChildren().addAll( gameMenu);

//	        scene.setOnKeyPressed(event -> {
//	            if (event.getCode() == KeyCode.ESCAPE) {
//	                if (!gameMenu.isVisible()) {
//	                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
//	                    ft.setFromValue(0);
//	                    ft.setToValue(1);
//	                    gameMenu.setVisible(true);
//	                    ft.play();
//	                }
//	                else {
//	                    FadeTransition ft = new FadeTransition(Duration.seconds(0.5), gameMenu);
//	                    ft.setFromValue(1);
//	                    ft.setToValue(0);
//	                    ft.setOnFinished(evt -> gameMenu.setVisible(false));
//	                    ft.play();
//	                }
//	            }
//	        });
			
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) {
		launch(args);
	}
}
