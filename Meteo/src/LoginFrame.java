import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
public class LoginFrame extends JFrame implements Runnable{
	private JTextField login;
	private JPasswordField password;
	
	LoginFrame(String name){
		super(name);
		password= new JPasswordField();
		login= new JTextField();
		setLayout(new java.awt.GridLayout(0, 1));
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w=300;
		int h=150;
		JLabel loginlabel=new JLabel("Login:");
		JLabel passwordlabel=new JLabel("Password:");
		add(loginlabel);
		add(login);
		add(passwordlabel);
		add(password);
		setBounds(screenSize.width/2-w/2, screenSize.height/2-h/2, w, h);
		setVisible(true);
	}
	public String getPassword() {
		return password.getpsw();
	}
	public String getLogin() {
		return login.getText();
	}
	@Override
	public void run() {
		password.done=false;
		this.setVisible(true);
		while(!password.done) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.setVisible(false);
		this.dispose();
		return;
	}
}
