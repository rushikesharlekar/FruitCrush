package game.frontend;

import game.backend.FruitGame;
import game.backend.Level;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class LevelsFrame extends JFrame {
	
	private MainFrame parent;

	
	public LevelsFrame(MainFrame main) throws IOException {
		this.setParent(main);
		
		setBounds(0, 0, 1024, 580);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		
		BufferedImage icon = ImageIO.read(new File("resources/images/icon.png"));
		setIconImage(icon);

		
		setResizable(false);

		
		BufferedImage myImage = ImageIO.read(new File("resources/images/background_map.png"));
		setContentPane(new ImagePanel(myImage));
		
		BufferedImage imageOne = ImageIO.read(new File("resources/images/one.png"));
		ImagePanel panelOne = new ImagePanel(imageOne, this, new FruitGame(Level.class));
		panelOne.setBounds(85,165,35, 35);
		
		add(panelOne);
		
		
		
	}


	public void createGame(FruitGame game){
		
//				
		FruitFrame frame;
		try {
			frame = new FruitFrame(game);
			frame.setVisible(true);
			
			frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				setVisible(true);
//				
				}
			});
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
	
	public MainFrame getParent() {
		return parent;
	}


	private void setParent(MainFrame parent) {
		this.parent = parent;
	}
}
