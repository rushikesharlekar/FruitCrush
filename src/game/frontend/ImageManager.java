package game.frontend;


import game.backend.Fruit;
import game.backend.FruitColor;
import game.backend.Element;
import game.backend.Nothing;
import game.backend.Wall;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ImageManager {

	private Map<String, Image> images;

	public ImageManager() {
		
				
		images = new HashMap<String, Image>();

		try {
			images.put(new Nothing().getKey(), ImageIO.read(new File("resources/images/nothing.png")));
			images.put(new Wall().getKey(), ImageIO.read(new File("resources/images/wall.png")));
			
			
			
			for (FruitColor cc: FruitColor.values()) 
                        {
				images.put(new Fruit(cc).getFullKey(), ImageIO.read(new File("resources/images/" + cc.toString().toLowerCase() + "Fruit.png")));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Image getImage(Element e) {
		return images.get(e.getFullKey());
	}
	
	//public Image getImageHint(FruitColor cc) {
		//return images.get(cc.toString().toLowerCase()+"Hint");
	//}
}
