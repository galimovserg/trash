import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Random;

import json.JSONArray;
import json.JSONObject;

public class DataBase implements Runnable{
	private static DataBase instance=null;
	private static String configPath="config.conf";
	private String DataPath;
	private JSONObject configuration;
	private User users[];
	private ArrayList<String> tokenlist;
	private ArrayList<WeatherStation> stationsList;
	private long driverinterval;
	private ArrayList<Object[]> dataPull;
	private ArrayList<Object[]> teleData;
	private DataBase() {
		tokenlist=new ArrayList<String>();
		newstationslist=new ArrayList<WeatherStation>();
		stationsList=new ArrayList<WeatherStation>();
		dataPull=new ArrayList<Object[]>();
		teleData=new ArrayList<Object[]>();
		loadDataBase();
		
	}
	static char[] open(File f) {
		
		char[] CB = new char[(int) f.length()];
		Reader reader;
		try {
			reader = new InputStreamReader(new FileInputStream(f), "UTF-8");
			try {
				reader.read(CB);
				reader.close();
				//System.out.println(CB);
				return CB;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		return null;

		   
	}
	void setDataPath(String path){
		DataPath=path;
		configuration.add("datapath", path);
		System.out.println(configuration.getJSONString(" "));
	}
	void loadConfiguration() {
		  File myFile = new File(configPath);
		  System.out.println("Open cofiguration...");
	      System.out.println(" File name: " + myFile.getName());
	      System.out.println(" Parent folder: " + myFile.getParent());
	      if(myFile.exists())
	    	  System.out.println(" File exists");
	      else
	    	  System.out.println(" File not found");
	         
	      System.out.println(" File size: " + myFile.length());
	      if(myFile.canRead())
	         System.out.println(" File can be read");
	      else
	    	 System.out.println(" File can not be read");
	      String conf=String.valueOf(open(myFile));
	      configuration=new JSONObject();
	      configuration=configuration.startparse(conf);
	      
	      try {
			DataPath=configuration.getString("datapath");
			File file =new File(DataPath);	
			if(file.exists()){
				double bytes = file.length();
				double kilobytes = (bytes / 1024);
				DataSize = kilobytes ;
				partitionTotal=file.getTotalSpace()/1024/1024;
				partitionFree=file.getFreeSpace()/1024/1024;
			}
			JSONArray usrs=configuration.getJSONArray("users");
			users=new User[usrs.getArray().length];
			int i=0;
			for(Object obj:usrs.getArray()) {
				users[i]=User.loadFromJSONObject((JSONObject)obj);
				i++;
			}
			
			JSONArray sts=configuration.getJSONArray("stations");
			//System.out.println(sts.print(" "));
			for(Object obj:sts.getArray()) {
				stationsList.add(WeatherStation.loadFromJSONObject((JSONObject)obj));
			}
			driverinterval=configuration.getLong("interval");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	void loadDataBase() {
		loadConfiguration();
	}
	public static DataBase getInstance(){
		if(instance!=null) {
			return instance;
		}else {
			return new DataBase();
		}
	}
	private static final int sizeOfToken=10;
	private String getToken() {
		String token="";
		Random randbytes=new Random();
		for(int i=0;i<sizeOfToken;i++) {
			long basel=(long)256*256*256*256;
			token+=(char)(randbytes.nextInt()%basel);
		}
		tokenlist.add(token);
		return token;
		
	}
	public String getAnalyticToken(String login, String password) {
		for(User user:users) {
			if(user.auth(login, password, 2)) {
				return getToken();
			}
		}
		return null;
	}
	public String getAdminToken(String login, String password) {
		for(User user:users) {
			if(user.auth(login, password, 1)) {
				return getToken();
			}
		}
		return null;
	}
	public ArrayList<WeatherStation> getAllStations(){
		return stationsList;
	}
	public void addMeasurement() {
		
	}
	private boolean newSt=false;
	private ArrayList<WeatherStation> newstationslist;
	public boolean hasNewStations() {
		// TODO Auto-generated method stub
		return newSt;
	}
	public synchronized void addNewStation(WeatherStation st) {
		newSt=true;
		newstationslist.add(st);
	}
	private long hashstatuses=0;
	private long partitionTotal;
	private long partitionFree;
	private double DataSize;
	
	
	private long getStatusesHash() {
		int i=1;
		long hashst=0;
		for(WeatherStation ws:stationsList) {
			hashst+=i*(ws.getStatus()+1);
			i++;
		}
		return hashst;
	}
	public String[][] getAllStationsData(){
		String[][] result=new String[stationsList.size()][5];
		int i=0;
		for(WeatherStation ws:stationsList) {
			result[i][0]=ws.getID();
			result[i][1]=ws.getName();
			if(ws.getStatus()==WeatherStation.isNoConnection) {
				result[i][2]="Нет связи";
			}else {
				result[i][2]="Работает";
			}
			result[i][3]=ws.getIP();
			Object[] ob=getLastStationTeleDataByID(Long.valueOf(ws.getID()));
			if(ob!=null) {
				SimpleDateFormat DATE_F = new SimpleDateFormat("ss:mm:kk");
				long diff=System.currentTimeMillis()-(long)ob[1];
				DecimalFormat myFormatter = new DecimalFormat("00");
			    
				long diffSeconds = diff / 1000 % 60;
				String diffSec = myFormatter.format(diffSeconds);
				long diffMinutes = diff / (60 * 1000) % 60;
				String diffMin = myFormatter.format(diffMinutes);
				long diffHours = diff / (60 * 60 * 1000) % 24;
				String diffHrs = myFormatter.format(diffHours);
				String dat = ""+diffSec+":"+diffMin+":"+diffHrs;
				result[i][4]=dat;
			}else {
				result[i][4]="";
			}
			i++;
		}
		return result;
	}
	public ArrayList<WeatherStation> getNewStationList(){
		newSt=false;
		return newstationslist;
	}
	public boolean hasNewStatuses() {
		long newhash=getStatusesHash();
		if(newhash!=hashstatuses) {
			hashstatuses=newhash;
			return true;
		}
		return false;
	}
	public synchronized void merge() {
		for(WeatherStation ws:newstationslist) {
			stationsList.add(ws);
		}
		newstationslist.clear();
	}
	public String[] getStationDataByID(long id) {
		for(WeatherStation ws:stationsList) {
			if(Long.valueOf(ws.getID())==id) {
				return ws.getDataRow();
			}
		}
		return null;
	}
	public synchronized Object[] getLastStationTeleDataByID(long id) {
		if(teleData!=null)
		for(Object[] ws:teleData) {
			if((long) ws[0]==id) {
				return ws;
			}
		}
		return null;
	}
	
	public void editStation(long stID, String stName, String stIP, GPS location) {
		for(WeatherStation ws:stationsList) {
			if(Long.valueOf(ws.getID())==stID) {
				ws.setData(stName, stIP, location);
				return;
			}
		}
	}
	
	public synchronized void addWeatherData(long StID, WeatherData wd) {
		Object data[]=new Object[] {StID,
				wd.getTime(),
				wd.getAtmosphericPressure(),
				wd.getHumidity(),
				wd.getPrecipitationAmount(),
				wd.getTemperature(),
				wd.getWindDirection(),
				wd.getWindSpeed()};
		int i=0;
		int k=-1;
		for(Object[] ws:teleData) {
			if((long) ws[0]==StID) {
				k=i;
			}
			i++;
		}
		
		if(k>=0) {
			teleData.remove(k);
		}
		teleData.add(data);
		dataPull.add(data);
	}
	public long getDriverIterval() {
		return driverinterval;
	}
	synchronized void saveDataPull() {
		try(FileWriter writer = new FileWriter(DataPath, true)){
			int i=0;
			System.out.println("Saving data...");
			for(Object[] datarow: dataPull) {
				String text = "["+datarow[0]+", "+datarow[1]+", "+datarow[2]+", "+datarow[3]+", "+datarow[4]+", "+datarow[5]+", "+datarow[6]+", "+datarow[7]+"]";
				//dataPull.remove(i);
				System.out.println(text);
				writer.write(text);
				writer.append('\n');
				i++;
			}
			writer.flush();
			int s=i;
			for(int j=0;j<s;j++) {
				dataPull.remove(0);
			}
			File file =new File(DataPath);	
			if(file.exists()){
				double bytes = file.length();
				double kilobytes = (bytes / 1024);
				DataSize = (kilobytes);
				partitionTotal=file.getTotalSpace()/1024/1024;
				partitionFree=file.getFreeSpace()/1024/1024;
			}
		}catch(IOException ex){
			System.out.println(ex.getMessage());
		} 	
		
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(1);
				if(dataPull!=null) {
					if(dataPull.size()>50) {
						saveDataPull();
					}
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public String getDataBasePath() {
		// TODO Auto-generated method stub
		return DataPath;
	}
	public double getDataBaseSize() {
		// TODO Auto-generated method stub
		return DataSize;
	}
	public double getPartitionFree() {
		// TODO Auto-generated method stub
		return partitionFree;
	}
	public double getPartitionTotal() {
		// TODO Auto-generated method stub
		return partitionTotal;
	}
	public void fixStation(long stID) {
		for(WeatherStation ws:stationsList) {
			if(Long.valueOf(ws.getID())==stID) {
				ws.setStatus(WeatherStation.isWorking);;
				return;
			}
		}
	}
}
