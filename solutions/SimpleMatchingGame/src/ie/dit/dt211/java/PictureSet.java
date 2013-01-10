package ie.dit.dt211.java;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Model
 * 
 * Manage disk-based images sets
 *
 */
public class PictureSet {
	// The numbers here are used to look up the folder with the corresponding images
	public static final int LOWERCASE = 1;
	public static final int UPPERCASE = 2;
	public static final int NUMERIC = 3;
	public static final int SYMBOLS = 4;
	
	// Location of picture images
	List<File> imageFiles = new ArrayList<File>();
	private ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
	
	public PictureSet(int setId) {
		File imagesFolder = new File("images/" + setId);
		Collections.addAll(imageFiles, imagesFolder.listFiles());
	}
	
	// Take the fist random 'count' images from the images folder
	public List<BufferedImage> takeRandom(int count) throws IOException {
		Collections.shuffle(imageFiles);
		int max = Math.min(count, imageFiles.size());
		for (int i = 0; i < max; i++) {
			images.add(ImageIO.read(imageFiles.get(i)));
		}
		return images;
	}
	
	public BufferedImage get(int index) {
		return images.get(index);
	}
}
