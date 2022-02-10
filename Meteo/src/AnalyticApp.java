
public class AnalyticApp implements Runnable{
	String login="";
	String password="";
	DataBase db;
	private String token=null;
	private LoginFrame pswrd;
	public AnalyticApp(DataBase db) {
		this.db=db;
		
	}
	
	@Override
	public void run() {
		
		while(token==null) {
			
			pswrd=new LoginFrame("Analytic");
			pswrd.run();
			password=pswrd.getPassword();
			login=pswrd.getLogin();
			token=db.getAnalyticToken(login, password);
		}
	}

}
