package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root,1000,700);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//======= Top ===============================
			HBox top = new HBox();
			top.setId("top");
			Button load = new Button("Wczytaj obrazek");
			Button clear = new Button("Wyczyść wyniki");
			top.getChildren().addAll(load, clear);
			root.setTop(top);
			
			
			//======== Left ==============================
			HBox leftContainer = new HBox();
			leftContainer.setId("leftContainer");
			HBox imageContainer = new HBox();
			imageContainer.setMaxWidth(640);
			imageContainer.setMaxHeight(480);
			imageContainer.setId("imageContainer");
			
			Image image = new Image("https://picsum.photos/640/480/?random");
			ImageView imageView = new ImageView(image);
			
			imageContainer.getChildren().add(imageView);
			leftContainer.getChildren().add(imageContainer);
			root.setLeft(leftContainer);
			
			
			
			//======= Bottom =============================
			VBox bottom = new VBox();
			bottom.setId("bottom");
			Label txt = new Label();
			txt.setId("txt");
			txt.setText("Wycinki w kolejności malejącej średniej składowej czerwonej.");
			bottom.getChildren().add(txt);
			root.setBottom(bottom);
			
			
			primaryStage.setScene(scene);
			primaryStage.setTitle("JavaFX Project 2");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
