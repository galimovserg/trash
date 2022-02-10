import java.util.ArrayList;

import json.JSONArray;
import json.JSONObject;

public class Driver implements Runnable {
	private ArrayList<WeatherStation> wlist;
	private DataBase db;
	private long interval=100;
	public Driver(DataBase db) {
		// TODO Auto-generated constructor stub
		this.db=db;
		wlist=db.getAllStations();
		interval=db.getDriverIterval();
	}
	
	@Override
	public void run() {
		for(WeatherStation ws:wlist) {
			
			Thread thread = new Thread(ws);
			thread.start();
		}
		while(true) {
			if(db.hasNewStations()) {
				
				ArrayList<WeatherStation> newlist=db.getNewStationList();
				for(WeatherStation ws:newlist) {
					Thread thread = new Thread(ws);
					thread.start();
				}
				db.merge();
				
			}
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for(WeatherStation ws:wlist) {
				if(ws.getStatus()==WeatherStation.isWorking) {
					//System.out.println(ws.toJSONObject().getJSONString(" "));
					JSONObject wd=new JSONObject();
					wd=wd.parse(ws.getData());
					//System.out.println(ws.getData());
					try {
						Object objs[]=wd.getJSONArray("data").getArray();
						for(Object obj:objs) {
							WeatherData wdata=new WeatherData(0);
							wdata.LoadFromJSONObject((JSONObject)obj);
							db.addWeatherData(Long.valueOf(ws.getID()), wdata);
						}
						
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
				}
			}
		}
	}

}
