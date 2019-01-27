package application;

import javafx.scene.image.WritableImage;

public class Picture implements Comparable<Picture> {
	private WritableImage image;
	private Double avgRed;
	
	public Picture(WritableImage image, Double avgRed) {
		this.image = image;
		this.avgRed = avgRed;
	}

	public WritableImage getImage() {
		return image;
	}

	public void setImage(WritableImage image) {
		this.image = image;
	}

	public Double getAvgRed() {
		return avgRed;
	}

	public void setAvgRed(Double avgRed) {
		this.avgRed = avgRed;
	}

	@Override
	public String toString() {
		return "Picture [image=" + image + ", avgRed=" + avgRed + "]";
	}

	@Override
	public int compareTo(Picture picture) {
		return Double.compare(avgRed, picture.avgRed);
	}
	

}
