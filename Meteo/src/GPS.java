import json.JSONObject;

public class GPS {
	//широта
	private double latitude;
	//долгота
	private double longitude;
	//высота
	private double altitude;
	GPS(double latitude,double longitude,double altitude){
		this.latitude=latitude;
		this.longitude=longitude;
		this.altitude=altitude;
	}
	double getLatitude() {
		return latitude;
	}
	double getLongitude() {
		return longitude;
	}
	double getAltitude() {
		return altitude;
	}
	JSONObject toJSONObject() {
		JSONObject json=new JSONObject();
		json.add("latitude", this.latitude);
		json.add("longitude", this.longitude);
		json.add("altitude", this.altitude);
		return json;
	}
	public static GPS loadFromJSONObject(JSONObject obj) throws Exception {
		// TODO Auto-generated method stub
		double latitude=obj.getDouble("latitude");
		double altitude=obj.getDouble("altitude");
		double longitude=obj.getDouble("longitude");
		return new GPS(latitude,longitude,altitude);
	}
}
