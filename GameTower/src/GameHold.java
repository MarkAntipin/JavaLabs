import javax.swing.*;


public class GameHold {
	private static JFrame gameFrame = new JFrame();
   
    public GameHold(String title,int level) {
    	gameFrame.setTitle(title);
    	build();
    	System.out.println("inside temp: " + level);
    	call(level);
    }

    private void build(){
        gameFrame.setSize(900, 700);; 
        gameFrame.setResizable(false);
        gameFrame.setLocationRelativeTo(null); 
        gameFrame.setVisible(true);
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 
   
    public  void call(int level) {
    	System.out.println("inside call: " + level);
        Tower t =new Tower(level);
        gameFrame.getContentPane().add(t);
    }
}