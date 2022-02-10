import javax.swing.JFrame;

public class AdminApp implements Runnable{
	private String login="";
	private String password="";
	private LoginFrame pswrd; 
	private WorkAdminFrame adminFrame;
	private String token=null;
	private DataBase db;
	private Driver dr;
	private String[][] StationData;
	public AdminApp(DataBase db, Driver dr) {
		this.db=db;
		this.dr=dr;
		adminFrame=new WorkAdminFrame(this);
		adminFrame.setVisible(false);
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		pswrd=new LoginFrame("Admin");
		while(token==null) {
			
			pswrd.run();
			password=pswrd.getPassword();
			login=pswrd.getLogin();
			token=db.getAdminToken(login, password);
			System.out.println(login+" "+password);
		}
		adminFrame.setVisible(true);
		StationData=db.getAllStationsData();
		adminFrame.setStationData(StationData);
		long counter=101;
		while(true) {
			try {
				Thread.sleep(10);
				if(db.hasNewStatuses()) {
					StationData=db.getAllStationsData();
					adminFrame.setStationData(StationData);	
				}
				if(selectedStation>=0) {
					String datarow[]=db.getStationDataByID(selectedStation);
					if(datarow!=null) {
						adminFrame.setTeleDataOfSelectedStation(datarow,db.getLastStationTeleDataByID(selectedStation));
					}
				}
				counter++;
				if(counter>100) {
					adminFrame.setDataBaseState(db.getDataBasePath(), db.getDataBaseSize(),db.getPartitionFree(), db.getPartitionTotal());
					StationData=db.getAllStationsData();
					adminFrame.setStationData(StationData);	
					counter=0;
				}
				} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	void AddNewStationButtonClicked() {
		String stName=adminFrame.getNewStationName();
		String stIP=adminFrame.getNewStationIP();
		long stID=adminFrame.getNewStationID();
		double stLatitude=adminFrame.getNewStationLatitude();
		double stLongitude=adminFrame.getNewStationLongitude();
		double stAltitude=adminFrame.getNewStationAltitude();
		GPS location=new GPS(stLatitude,stLongitude,stAltitude);
		WeatherStation st=new WeatherStation(stName, stIP, stID, location);
		db.addNewStation(st);
		StationData=db.getAllStationsData();
		adminFrame.setStationData(StationData);
	}
	public void editStationButtonClicked() {
		String stName=adminFrame.getEditedStationName();
		String stIP=adminFrame.getEditedStationIP();
		long stID=adminFrame.getEditedStationID();
		double stLatitude=adminFrame.getEditedStationLatitude();
		double stLongitude=adminFrame.getEditedStationLongitude();
		double stAltitude=adminFrame.getEditedStationAltitude();
		GPS location=new GPS(stLatitude,stLongitude,stAltitude);
		db.editStation(stID, stName, stIP, location);
		StationData=db.getAllStationsData();
		adminFrame.setStationData(StationData);
	}
	long selectedStation=-1;
	public void selectStation(long id) {
		selectedStation=id;
		String datarow[]=db.getStationDataByID(selectedStation);
		if(datarow!=null) {
			adminFrame.setDataOfSelectedStation(datarow);
			adminFrame.setTeleDataOfSelectedStation(datarow,db.getLastStationTeleDataByID(id));
		}
	}
	
	public void savePathButtonClicked() {
		db.setDataPath(adminFrame.getNewPath());
	}
	public void fixConnectionButton() {
		long stID=adminFrame.getEditedStationID();
		db.fixStation(stID);
	}

}
