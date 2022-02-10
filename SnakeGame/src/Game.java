import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {
	JPanel panel0;
	Thread t1;
	Game(){
		super("Snake");
		setDefaultCloseOperation(EXIT_ON_CLOSE); 
		panel0=new JPanel();
		panel0.setLayout(null);
		MapGame Map=new MapGame(0,0,1900,1040);
		
		panel0.add(Map);
		
		t1= new Thread(Map);
		t1.start();
		
		
		setContentPane(panel0);
	 	setSize(1900,1040);
	 	setResizable(false);
	 	setVisible(true);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Game();
	}

}
