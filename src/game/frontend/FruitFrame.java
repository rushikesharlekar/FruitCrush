package game.frontend;

import game.backend.FruitGame;
import game.backend.Cell;
import game.backend.Element;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;


public class FruitFrame extends JFrame {
	private static final int CELL_SIZE = 65;
	private static final int SCORE_HEIGHT = 80;

	private BoardPanel bp;
	private ImageManager images;
	private FruitGame game;

	public FruitFrame(FruitGame game) throws IOException {
		this.game = game;
		
		images = new ImageManager();

		setLayout(null);
		setResizable(false);
		setSize(game.getSize() * CELL_SIZE + 20, game.getSize() * CELL_SIZE + SCORE_HEIGHT );
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);


		BufferedImage icon = ImageIO.read(new File("resources/images/icon.png"));
		setIconImage(icon);

		try
		{

		}catch(IllegalArgumentException ex){
			System.err.println(ex.getMessage());
		}

		
		bp = new BoardPanel(game.getSize(), game.getSize(), CELL_SIZE);
		add(bp);
		game.initGame();
		gridUpdated();


		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {

			}
		});
	}

        public void gridUpdated() {
            try {
                    for (int i = 0; i < game().getSize(); i++) {
                            for (int j = 0; j < game().getSize(); j++) {
                                    setImageCell(i, j, false);
                            }
                    }
                    bp.paintImmediately(bp.getBounds());
            } catch (Exception e) {
                    System.out.println(e.getMessage());
            }
    }
	
	private void setImageCell(int i, int j, boolean hint) {
		try {
			Cell cell = FruitFrame.this.game.get(i, j);
			Element element = cell.getContent();
			
			bp.clearImage(i, j);
			
			Image image;
			image = images.getImage(element);
			
			Image nothing= ImageIO.read(new File("resources/images/nothing.png"));
			bp.setImage(i, j, nothing);
			
			bp.appendImage(i, j, nothing);
			
			
			bp.appendImage(i, j, image);
			
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}


	private FruitGame game() {
		return game;
	}

}
