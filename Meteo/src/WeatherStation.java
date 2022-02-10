import java.util.ArrayList;

import json.JSONArray;
import json.JSONObject;

public class WeatherStation implements Runnable{
	private WeatherData WD[];
	private GPS location;
	private String ip;
	private long id;
	private String name;
	
	private int pointer;
	private static final int maxSize=1024;
	private long interval=1000;
	private int status;
	public static final int isWorking=0;
	public static final int isNotWorking=1;
	public static final int isNoConnection=2;
	
	WeatherStation(String name, String ip, long id, GPS loc){
		pointer=0;
		WD=new WeatherData[maxSize];
		status=isWorking;
		this.name=name;
		this.ip=ip;
		this.location=loc;
		this.id=id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status=status;
	}
	private int genStatus() {
		int st=(int)(Math.random()*1000.0);
		if(st<=10) return isNoConnection;
		return isWorking;
	}
	double MeasureTemperature() {
		return Math.sin(System.currentTimeMillis()/10000+2)*5+10;
	}
	double MeasureAtmosphericPressure() {
		return Math.sin(System.currentTimeMillis()/30000)*10000+100000;
	}
	double MeasureHumidity() {
		return Math.sin(System.currentTimeMillis()/10000)*50+50;
	}
	double MeasureWindSpeed() {
		return (Math.sin(System.currentTimeMillis()/10000)+1);
	}
	int MeasureWindDirection() {
		return (int)Math.round((Math.sin(System.currentTimeMillis())+1)/2*360);
	}
	double MeasurePrecipitationAmount() {
		return Math.random()*10;
	}
	void MeasureData(WeatherData measurement) {
		measurement.setAtmosphericPressure(MeasureAtmosphericPressure());
		measurement.setHumidity(MeasureHumidity());
		measurement.setWindSpeed(MeasureWindSpeed());
		measurement.setTemperature(MeasureTemperature());
		measurement.setWindDirection(MeasureWindDirection());
		measurement.setPrecipitationAmount(MeasurePrecipitationAmount());
	}
	private boolean pause=false;
	String getData(){
		pause=true;
		JSONObject JSONResult=new JSONObject();
		ArrayList<JSONObject> objects=new ArrayList<JSONObject>();
		for(int i=pointer;i<maxSize;i++) {
			if(WD[i]!=null)
			 objects.add(WD[i].toJSONObject());
		}
		for(int i=0;i<pointer;i++) {
			if(WD[i]!=null)
			 objects.add(WD[i].toJSONObject());
		}
		for(int i=0;i<maxSize;i++) {
			WD[i]=null;	
		}
		pointer=0;
		JSONResult.addArray("data",objects.toArray());
		pause=false;
		return JSONResult.getJSONString(" ");
	}
	
	@Override
	public void run() {
		while(true) {
			while(pause) {
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			WeatherData measurement=new WeatherData(System.currentTimeMillis());
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MeasureData(measurement);
			WD[pointer%maxSize]=measurement;
			pointer++;
			if(status==isWorking)
				status=genStatus();
		}
		
	}
	public JSONObject toJSONObject() {
		JSONObject obj=new JSONObject();
		obj.add("name", name);
		obj.add("ip", ip);
		obj.add("id", id);
		obj.add("location", location.toJSONObject());
		return obj;
	}
	public static WeatherStation loadFromJSONObject(JSONObject obj) throws Exception {
		String ip=obj.getString("ip");
		String name=obj.getString("name");
		JSONObject location=(JSONObject)obj.getObj("location");
		long id=obj.getLong("id");
		GPS gpslocation=GPS.loadFromJSONObject(location);
		return new WeatherStation(name, ip, id, gpslocation);
	}
	public String getID() {
		// TODO Auto-generated method stub
		return String.valueOf(id);
	}
	public String getName() {
		// TODO Auto-generated method stub
		return name;
	}
	public String getIP() {
		// TODO Auto-generated method stub
		return ip;
	}
	public String[] getDataRow() {
		String res[]=new String[] {String.valueOf(id),name,ip,String.valueOf(location.getLatitude()),String.valueOf(location.getLongitude()),String.valueOf(location.getAltitude())};
		return res;
	}
	public void setData(String stName, String stIP, GPS location2) {
		this.name=stName;
		this.ip=stIP;
		this.location=location2;
	}
	
}
