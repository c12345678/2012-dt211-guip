package ie.dit.dt211.java;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import static java.lang.System.err;

/**
 * Model
 *
 * Manages the game tiles using an associated picture set
 */
public class GameTiles {
	private PictureSet set;
	private int width;
	private int height;
	private int matchCount;
	private BufferedImage[][] tiles;

	public GameTiles(PictureSet set, int width, int height, int matchCount) {
		this.set = set;
		this.width = width;
		this.height = height;
		this.matchCount = matchCount;
		tiles = new BufferedImage[width][height];
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public BufferedImage getImage(int x, int y) {
		return tiles[x][y];
	}
	
	/*
	 *  Take a random selection of images from the bigger pool and generate matchCount
	 *  times that mount and randomise their positions in a grid. Note the usefulness of
	 *  the Collections class here.
	 */
	public void assignTiles() {
		List<BufferedImage> images = null;
		try {
			images = set.takeRandom(width * height / matchCount);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<BufferedImage> randomisedImages = new ArrayList<BufferedImage>(images);
		for (int n = 1; n < matchCount; n++) {
			randomisedImages.addAll(images);
		}
		Collections.shuffle(randomisedImages);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				tiles[x][y] = randomisedImages.get(x * height + y);
			}
		}
	}

	public int count() {
		return width * height;
	}
}
