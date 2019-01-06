package application;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class Main extends Application {
	int count = 0;
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 947, 660);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			Canvas imageCanvas = new Canvas(640, 480);

			// ======= Top ===============================
			HBox top = new HBox();
			top.setId("top");
			Button load = new Button("Wczytaj obrazek");
			Button clear = new Button("Wyczyść wyniki");
			top.getChildren().addAll(load, clear);
			root.setTop(top);
			

			// ======= Right =============================
			FlowPane rightContainer = new FlowPane();
			rightContainer.setId("rightContainer");
			rightContainer.setPrefWidth(295);

			ArrayList<HBox> boxs = new ArrayList<>();

			for (int i = 0; i < 25; i++) {
				boxs.add(i, new HBox(5));
				boxs.get(i).getStyleClass().add("box");
				rightContainer.getChildren().add(boxs.get(i));
			}

			root.setRight(rightContainer);

			
			// ======== Left ==============================
			Group leftContainer = new Group();

			HBox imageContainer = new HBox();
			imageContainer.setMaxWidth(640);
			imageContainer.setMaxHeight(480);
			imageContainer.setId("imageContainer");

			Image image = new Image("https://picsum.photos/640/480/?random");
			ImageView imageView = new ImageView(image);

			imageContainer.getChildren().add(imageView);
			leftContainer.getChildren().add(imageContainer);

			leftContainer.getChildren().add(imageCanvas);

			
			
			load.setOnAction(event -> { // *** load button listener ***
				System.out.println("Nowy img");
				
			});

			
			
			imageCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, eventHandler -> {
				
				
				Canvas copyCanvas = new Canvas(41, 41);
				GraphicsContext copyGraphicsContext = copyCanvas.getGraphicsContext2D();
				
				boxs.get(count).getChildren().add(copyCanvas);
				count++;
				
				
				PixelReader reader = image.getPixelReader();

				int width = 41;
				int height = 41;
				
				WritableImage copyPartImg = new WritableImage(width , height );
				PixelWriter writer = copyPartImg.getPixelWriter();
				
				int startX = (int) eventHandler.getX() - 20;
				int startY = (int) eventHandler.getY() - 20;
				
				for(int x = 0; x < width; x++) {
					for(int y = 0; y < height; y++) {
						Color color = reader.getColor(startX + x, startY + y);
						writer.setColor(x, y,
								Color.color(
										color.getRed(), 
										color.getGreen(), 
										color.getBlue()
										));
					}
				}

				copyGraphicsContext.drawImage(copyPartImg, 0, 0);
			});

			root.setLeft(leftContainer);

			
			// ======= Bottom =============================
			VBox bottom = new VBox();
			bottom.setId("bottom");
			Label txt = new Label();
			txt.setId("txt");
			txt.setText("Wycinki w kolejności malejącej średniej składowej czerwonej.");
			bottom.getChildren().add(txt);
			root.setBottom(bottom);

			
			// =============================================
			primaryStage.setScene(scene);
			primaryStage.setTitle("JavaFX Project 2");
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
