package tetris;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.JButton;


public class Title extends JPanel implements KeyListener {

	private static final long serialVersionUID = 1L;
	private BufferedImage instructions, tetris_image;
	private WindowGame window;
	private BufferedImage[] playButton = new BufferedImage[2];
	private Timer timer;
	Font PressStart;
	
	public Title(WindowGame window){
                instructions = ImageLoader.loadImage("/arrow1.png");
                tetris_image = ImageLoader.loadImage("/tetris.png");
		timer = new Timer(1000/60, new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
			}
			
		});
		timer.start();
		this.window = window;

		try {
			PressStart = Font.createFont(Font.TRUETYPE_FONT, new File("PressStart2P.ttf")).deriveFont(13f);
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("PPressStart2P.ttf")));
		}
		catch (IOException | FontFormatException e) {

		}

	}


	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WindowGame.WIDTH, WindowGame.HEIGHT);
		g.drawImage(tetris_image, WindowGame.WIDTH/2 - tetris_image.getWidth()/2,30 - tetris_image.getHeight()/2 + 160, null);
		g.setColor(Color.WHITE);
		g.setFont(PressStart);
		g.drawString("Press SPACE to play!", 180, WindowGame.HEIGHT / 2 + 80);
		g.drawImage(instructions, WindowGame.WIDTH/2 -110,WindowGame.HEIGHT/2 + 120, null);

	}	

	int space = 0;
    @Override
    public void keyTyped(KeyEvent e) {
        if((e.getKeyChar() == KeyEvent.VK_SPACE) && (space == 0)) {
				space = 1;
				window.startTetris();
			}
		if((e.getKeyChar() == KeyEvent.VK_ENTER) && (space == 1)) {
				space = 0;
				window.startTetris();
		}
    }


    @Override
    public void keyPressed(KeyEvent e) {}

    @Override
    public void keyReleased(KeyEvent e) {}
}
