import json.JSONObject;

public class User {
	private String name;
	private String password;
	private String login;
	private int rank;
	User(String name,String password,String login, int rank){
		this.login=login;
		this.password=password;
		this.rank=rank;
		this.name=name;
	}
	public static User loadFromJSONObject(JSONObject jo) {
		try {
			String name=jo.getString("name");
			String password=jo.getString("password");
			String login=jo.getString("login");
			int rank=(int) jo.getLong("rank");
			return new User(name,password,login,rank);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public boolean auth(String login,String password, int rank) {
		if(login.equals(this.login)&&password.equals(this.password)&&rank==this.rank) {
			return true;
		}
		return false;
	}
}
