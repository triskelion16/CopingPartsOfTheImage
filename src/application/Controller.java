package application;

import java.util.ArrayList;

public class Controller {
	private ArrayList<Picture> pictures = new ArrayList<>();

	public ArrayList<Picture> getPictures() {
		return pictures;
	}
	
	public void addPicture(Picture picture) {
		pictures.add(picture);
	}
	
	public void deletePictures() {
		pictures.clear();
	}

}
