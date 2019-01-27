package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
	Controller controller = new Controller();
	
	ArrayList<HBox> boxs = new ArrayList<>();
	int count = 0;

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			Scene scene = new Scene(root, 947, 660);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

			// ======= Top ===============================
			HBox top = new HBox();
			top.setId("top");
			Button load = new Button("Wczytaj obrazek");
			Button clear = new Button("Wyczyść wyniki");
			top.getChildren().addAll(load, clear);
			root.setTop(top);

			load.setOnAction(event -> { // *** load button listener ***
				System.out.println("Nowy img");

			});

			// ======= Right ==========================
			FlowPane rightContainer = new FlowPane();
			rightContainer.setId("rightContainer");
			rightContainer.setPrefWidth(295);

			for (int i = 0; i < 25; i++) { //właściwie może być tablica
				boxs.add(i, new HBox());
				boxs.get(i).getStyleClass().add("box");
				rightContainer.getChildren().add(boxs.get(i));
			}

			root.setRight(rightContainer);

			clear.setOnAction(event -> { // *** clear button listener ***
				
				for(HBox box : boxs) {
					box.getChildren().clear();
					count = 0;
				}
				
				controller.deletePictures();
			});

			// ======== Left ==============================
			Canvas imageCanvas = new Canvas(640, 480);
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

			imageCanvas.addEventHandler(MouseEvent.MOUSE_PRESSED, eventHandler -> { // logika do Controller
				int width = 41;
				int height = 41;
				double red = 0;
				double avgRed = 0;

				Canvas copyCanvas = new Canvas(width, height);
				GraphicsContext copyGraphicsContext = copyCanvas.getGraphicsContext2D();

				PixelReader reader = image.getPixelReader();

				WritableImage copyPartImg = new WritableImage(width, height);
				PixelWriter writer = copyPartImg.getPixelWriter();
				
				for(HBox box : boxs) { //czyszcenie wszystkich HBox
					//box.getChildren().clear(); 
				}
				
				//boxs.get(3).getChildren().clear();
				boxs.get(count).getChildren().add(copyCanvas); 
				count++;

				int startX = (int) eventHandler.getX() - 20;
				int startY = (int) eventHandler.getY() - 20;

				for (int x = 0; x < width; x++) {
					for (int y = 0; y < height; y++) {
						Color color = reader.getColor(startX + x, startY + y);
						writer.setColor(x, y, Color.color(color.getRed(), color.getGreen(), color.getBlue()));

						red += color.getRed();
					}
				}

				avgRed = red / (Math.pow(41, 2));
				//System.out.println(avgRed);
				
				Picture picture = new Picture(copyPartImg, avgRed);
				controller.addPicture(picture);
				
				ArrayList<Picture> imgs = controller.getPictures();
				
				System.out.println(imgs.size());
				
				Collections.sort(imgs);
				
				
				for(Picture p : imgs) {
					System.out.println(p);
				}

				//copyGraphicsContext.drawImage(copyPartImg, 0, 0);
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
