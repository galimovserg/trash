import java.awt.event.KeyEvent;

import javax.swing.JTextField;

public class JPasswordField extends JTextField{
		
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8952919583445645839L;
	private String psw="";
	public Boolean done=false;
	public String getpsw() {
		return psw;
	}
	JPasswordField(){
		super();
		addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char ch=e.getKeyChar();
				if(ch=='\n') {
					done=true;
				}else
				if(ch=='\b') {
					if(psw.length()>0) {
						psw=String.valueOf(String.copyValueOf(psw.toCharArray(), 0, psw.length()-1));
					}
				}else {
					psw+=String.valueOf(ch);
				}
				String s=""; 
				for(int i=0;i<psw.length();i++) {
					s+="*";
				}
				setText(s);
		 		e.consume();
			}

		 });
	}
}
