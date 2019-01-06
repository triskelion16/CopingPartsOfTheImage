package application;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class Controller {
	static final int width = 41;
	static final int height = 41;
	
	

	public static PixelWriter readPartImg(Image image, Canvas imageCanvas, MouseEvent eventHandler) {
		//GraphicsContext imageGraphicsContext = imageCanvas.getGraphicsContext2D();
		Canvas copyCanvas = new Canvas(width, height);
		GraphicsContext copyGraphicsContext = copyCanvas.getGraphicsContext2D();
		
		PixelReader reader = image.getPixelReader();
		
		WritableImage copyPartImg = new WritableImage(width, height);
		PixelWriter writer = copyPartImg.getPixelWriter();
		
		int startX = (int) eventHandler.getX() - 20;
		int startY = (int) eventHandler.getY() - 20;
		
		for(int x = 0; x < width; x++) {
			for(int y = 0; y < height; y++) {
				Color color = reader.getColor(startX, startY);
				writer.setColor(x, y,
						Color.color(
								color.getRed(), 
								color.getGreen(), 
								color.getBlue()
								));
			}
		}
		
		//copyGraphicsContext.drawImage(copyPartImg, 0, 0);
		
		
		System.out.println(startX + " | " + startY);
		return writer;
		
	}
}
