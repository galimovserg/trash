package json;

public class JSONArray {
	private Object[] objects;
	public JSONArray(Object[] objects){
		this.objects=objects;
	}
	public Object[] getArray() {
		return objects;
	}
	public String print(String tab) {
		String res="[";
		for(int i=0;i<objects.length;i++) {
			if(objects[i] instanceof JSONObject) {
				res+=((JSONObject)objects[i]).getJSONString(tab+"  ");
			}else
			if(objects[i] instanceof JSONArray) {
				res+=((JSONArray)objects[i]).print(tab+"  ");
			}else if(objects[i] instanceof String) {
				res+="\"";
				res+=objects[i];
				res+="\"";
			}else {
				res+=objects[i];
			}
			
			if(i!=objects.length-1) {
				res+=", ";
			}
		}
		res+="]";
		return res;
	}
}
