import json.JSONArray;
import json.JSONObject;

public class Start {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		DataBase db= DataBase.getInstance();
		Driver dr=new Driver(db);
		
		Thread thread0 = new Thread(db);
		thread0.start();
		
		AdminApp admin=new AdminApp(db,dr);
		AnalyticApp analytic=new AnalyticApp(db);
		Thread thread1 = new Thread(analytic);
		thread1.start();

		 
		
		System.out.print("msg");
		Thread thread2 = new Thread(admin);
		thread2.start();
		
		Thread thread3 = new Thread(dr);
		thread3.start();
	}

}
