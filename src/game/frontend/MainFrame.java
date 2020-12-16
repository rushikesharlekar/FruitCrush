package game.frontend;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import game.backend.FruitGame;




public class MainFrame extends JFrame {
	

	public MainFrame() throws IOException  {
		
		
		setBounds(0, 0, 1024, 580);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
		BufferedImage icon = ImageIO.read(new File("resources/images/icon.png"));
		setIconImage(icon);
		
		
		setResizable(false);
		
		
		BufferedImage myImage = ImageIO.read(new File("resources/images/background_intro.jpg"));
		setContentPane(new ImagePanel(myImage));
		
		PlayButton playButton = new PlayButton (this);
		add(playButton);
		
		
		
		try
		{
	
			
		}catch( IllegalArgumentException ex){
			System.err.println(ex.getMessage());
		}
		
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void play() throws IOException{
		LevelsFrame levelsFrame = new LevelsFrame(this);
		levelsFrame.setVisible(true);
		
		levelsFrame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				setVisible(true);
			}
		});
	}
	
	public void createGame(FruitGame game){
			
		FruitFrame frame;
		try {
			frame = new FruitFrame(game);
			frame.setVisible(true);
			
			frame.addWindowListener(new WindowAdapter() {public void windowClosing(WindowEvent e) {
				setVisible(true);
				
				}
			});
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}

