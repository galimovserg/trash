package json;
import java.util.ArrayList;

public class JSONObject {
	private String name;
	private KeyValueMap objects;
	
	public JSONObject(){
		this.objects=new KeyValueMap();
	}
	public Object getObj(String name) {
		
		return objects.getValue(name);
	}
	public Byte getByte(String name) throws Exception {
		Object value=getObj(name);
		if(value instanceof Byte) {
			return (Byte) value;
		}else {
			throw new Exception("wrong format");
		}
	}
	public Integer getInteger(String name) throws Exception {
		Object value=getObj(name);
		if(value instanceof Integer) {
			return (Integer) value;
		}else {
			throw new Exception("wrong format");
		}
		
		
	}
	public long getLong(String name) throws Exception {
		Object value=getObj(name);
		if(value instanceof Long) {
			return (Long) value;
		}else {
			throw new Exception("wrong format");
		}
	}
	public String getString(String name) throws Exception {
		Object value=getObj(name);
		if(value==null) {System.out.println("null");}
		if(value instanceof String) {
			return (String) value;
		}else {
			throw new Exception("wrong format");
		}
		
	}
	public double getDouble(String name) throws Exception {
		Object value=getObj(name);
		if(value instanceof Double) {
			return (double) value;
		}else {
			throw new Exception("wrong format");
		}
	}
	public boolean getBoolean(String name)  throws Exception {
		Object value=getObj(name);
		if(value instanceof Boolean) {
			return (boolean) value;
		}else {
			throw new Exception("wrong format");
		}
	}
	public JSONArray getJSONArray(String name) throws Exception {
		Object value=getObj(name);
		if(value instanceof JSONArray) {
			return (JSONArray) value;
		}else {
			throw new Exception("wrong format");
		}
	}
	
	
	public void add(String name, Object value) {
		
		objects.add(name, value);
	}
	
	public void addArray(String name, Object[] values) {
		objects.add(name, new JSONArray(values));
	}
	
	private int i;
	int findChar(String s,char ch) {
		while(i<s.length()&&ch!=s.charAt(i)) {
			i++;
		}
		return i;
	}
	
	String NextName(String json) {
		int startIndex=findChar(json,'\"');
		i++;
		int endIndex=findChar(json,'\"');
		
		return json.substring(startIndex+1, endIndex);
	}
	String parseString(String json) {
		int startIndex=i;
		
		i++;
		int endIndex=findChar(json,'\"');
		while(json.charAt(endIndex-1)=='\\') {
			i++;
			endIndex=findChar(json,'\"');
		}
		
		return json.substring(startIndex+1, endIndex);
	}
	private JSONArray parseArray(String json) {
		// TODO Auto-generated method stub
		//System.out.println(json.substring(0, i));
		//System.out.println("new array...");
		ArrayList<Object> objects=new ArrayList<Object>();
		
		while(json.charAt(i)!=']') {
			
			i++;
			Object obj=NextValue(json);
			//System.out.println(json.substring(0, i));
			//System.out.println("new element");
			objects.add(obj);
			//System.out.println(json.substring(0,i));
			while(json.charAt(i)!=']'&&json.charAt(i)!=',') {
				i++;
			}
			
		}
		
		i++;
		
		return new JSONArray(objects.toArray());
	}
	private Object parseAtomicValue(String str) {
		// TODO Auto-generated method stub
		if(str.contains(".")) {
			return Double.parseDouble(str);
		}else if(str.contains("true")){
			return true;
		}else if((str.contains("false"))) {
			return false;
		}else {
			
			str=str.replace(" ", "");
			str=str.replace("\n", "");
			str=str.replace("	", "");
			str=str.replace("	", "");
			str=str.replace("\r", "");
			return (Long)Long.parseLong(str);
		}
		//return null;
	}
	Object NextValue(String json) {
		
		
		int startValueIndex=i;
		while(true) {
			switch(json.charAt(i)) {
				case ' ':{
					
					
					break;
				}
				case '\"':{
					
					String vl=parseString(json);
					
					i++;
					while(json.charAt(i)!=','&&json.charAt(i)!='}'&&json.charAt(i)!=']') {
						i++;
					}
					
					return vl;
				}
				case '}':{
					int endValueIndex=i;
					return parseAtomicValue(json.substring(startValueIndex,endValueIndex));	
				}
				case '{':{
					return parse(json);
					
				}
				case '[':{
					//i++;
					Object arr= parseArray(json);
					
					while(json.charAt(i)!=','&&json.charAt(i)!='}'&&json.charAt(i)!=']') {
						i++;
					}
					
					return arr;
					
				}
				case ',':{
					int endValueIndex=i;
					i++;
					return parseAtomicValue(json.substring(startValueIndex,endValueIndex));
					
				}
				case ']':{
					int endValueIndex=i;
					
					return parseAtomicValue(json.substring(startValueIndex,endValueIndex));
					
				}
			
			}
			
			
			i++;
			
		}
		
	}
	
	public JSONObject startparse(String json) {
		i=0;
		return parse(json);
	}
	public JSONObject parse(String json){
		JSONObject jo=new JSONObject();
		findChar(json,'{');
		while(json.charAt(i)!='}'&&i<json.length()) {
			
			String name=NextName(json);
			//System.out.println(name);
		
			findChar(json,':');
			i++;
			jo.add(name, NextValue(json));
			
		}
		i++;
		
		return jo;
	}
	private String getObjectsString(String name, Object obj, String tab) {
		String res="\""+name+"\""+" : ";
		
		if(obj instanceof JSONArray) {
			return res+((JSONArray)obj).print(tab);
		}
		if(obj instanceof JSONObject) {
			return res+((JSONObject)obj).getJSONString(tab);
		}
		if(obj instanceof String) {
			return res+"\""+(String)obj+"\"";
		}
		if(obj instanceof Integer) {
			return res+(Integer)obj;
		}
		if(obj instanceof Long) {
			return res+(Long)obj;
		}
		if(obj instanceof Double) {
			return res+(Double)obj;
		}
		return res;
	}
	
	public String getJSONString(String tab) {
		String res="{\n";
		KeyValueMap.Item item = objects.gethead();
		while(item!=objects.getlast()) {
			res+=tab+"  "+getObjectsString(item.getKey(),item.getValue(),tab+"  ")+",\n";
			item=item.getNext();
		}
		res+=tab+"  "+getObjectsString(item.getKey(),item.getValue(), tab+"  ")+"\n";
		res+=tab+"}";
		return res;
	}
	public String getName() {
		return name;
	}
}
