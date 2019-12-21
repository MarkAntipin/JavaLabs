import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MenuFrame extends JFrame{
	ImagePanel panel = new ImagePanel();
	JButton newGame = new JButton(panel.imageNewGame);

	public MenuFrame() {
		init();
		addMenuFrameActionListener();
	}
	
	public void init(){ 
		 newGame.setBounds(520,250, 200, 45);
		 newGame.setBorder(null);

         this.add(newGame);
         this.add(panel);
	}
	
	public void addMenuFrameActionListener(){
       newGame.addActionListener(new newGameAction());

	}
	
	public class ImagePanel extends JPanel{
		private ImageIcon imageIcon = new ImageIcon("images/untitle.jpg");
		private ImageIcon imageNewGame = new ImageIcon("images/newgame.jpg");
		private Image image = imageIcon.getImage();
			
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
		    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
		        
		    }
		 }
	}

	class newGameAction implements ActionListener { 
		public void actionPerformed(ActionEvent e){
			GameHold mDraw =new GameHold("Tower Of Hanoi",1);
			try {
				Clip clip = AudioSystem.getClip();
				System.out.println(clip.getFormat());
				clip.stop();
			}
			catch (LineUnavailableException e1) {
				e1.printStackTrace();
			}
		} 
	}


   