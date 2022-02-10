import json.JSONObject;

public class WeatherData {
	private double Temperaure;
	private double AtmosphericPressure;
	private double Humidity;
	private double WindSpeed;
	//Azimut degrees
	private Integer WindDirection;
	private double PrecipitationAmount;
	private long Time;
	WeatherData(long Time){
		this.Time=Time;
	}
	void setTemperature(double Temperaure) {
		this.Temperaure=Temperaure;
	}
	void setAtmosphericPressure(double AtmosphericPressure) {
		this.AtmosphericPressure=AtmosphericPressure;
	}
	void setHumidity(double Humidity) {
		this.Humidity=Humidity;
	}
	void setWindSpeed(double WindSpeed) {
		this.WindSpeed=WindSpeed;
	}
	void setWindDirection(Integer WindDirection) {
		this.WindDirection=WindDirection;
	}
	void setPrecipitationAmount(double PrecipitationAmount) {
		this.PrecipitationAmount=PrecipitationAmount;
	}
	long getTime() {
		return this.Time;
	}
	double getTemperature() {
		return this.Temperaure;
	}
	double getAtmosphericPressure() {
		return this.AtmosphericPressure;
	}
	double getHumidity() {
		return this.Humidity;
	}
	double getWindSpeed() {
		return this.WindSpeed;
	}
	Integer getWindDirection() {
		return this.WindDirection;
	}
	double getPrecipitationAmount() {
		return this.PrecipitationAmount;
	}
	public void LoadFromJSONObject(JSONObject json) {
		try {
			this.Temperaure=json.getDouble("Temperaure");
			this.AtmosphericPressure=json.getDouble("AtmosphericPressure");
			this.Humidity=json.getDouble("Humidity");
			this.WindSpeed=json.getDouble("WindSpeed");
			this.WindDirection=(int)json.getLong("WindDirection");
			this.PrecipitationAmount=json.getDouble("PrecipitationAmount");
			this.Time=json.getLong("Time");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	JSONObject toJSONObject() {
		JSONObject json=new JSONObject();
		json.add("Temperaure", Temperaure);
		json.add("AtmosphericPressure", AtmosphericPressure);
		json.add("Humidity",Humidity);
		json.add("WindSpeed",WindSpeed);
		json.add("WindDirection",WindDirection);
		json.add("PrecipitationAmount",PrecipitationAmount);
		json.add("Time",Time);
		return json;
	}
	
}
