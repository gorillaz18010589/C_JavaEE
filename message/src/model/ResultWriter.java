package model;
//fromJson(String json, Type typeOfT):從Json字串解析回傳到資料結構上(1.要轉型的Json字串,2.要轉型的資料結構類型)(回傳<T> T)

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
public class ResultWriter {
	
private static String defalutContentType ="application/json, charset=UTF-8"; 
	
	private static Map<String,String> map = new HashMap(); 
	private static  ResultWriter instance;
	
	private HttpServletResponse response;
	PrintWriter printWriter;
	
	
//	public static ResultWriter getInstance(HttpServletResponse response) {
//		if(instance == null) {
//			instance = new ResultWriter(response);
//		}
//		return instance;
//	}
//	
//	
//	public ResultWriter(HttpServletResponse response) {
//		try {
//			this.response = response;
//			response.setContentType("text/html; charset=UTF-8");
//			PrintWriter printWriter = response.getWriter();
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//	
	
//	public static void write(HttpServletResponse response, Map map, String contentType) throws Exception {
//		response.setContentType(contentType);
//		Gson gson = new Gson();
//		String jsonMsg = gson.toJson(map);
//		PrintWriter printWriter = response.getWriter();
//		printWriter.write(jsonMsg);
//		printWriter.close();
//		System.out.println("ResultWriter.write()" +"/jasonMsg:" + jsonMsg);
//	}
//	
	
	/*1.response訊息設定
	 * @param HttpServletResponse response => 1.Servlet的Response
	 * @param Map map => 2.要傳遞得訊息key:value
	 * @param String contentType => 3.contentType格式
	 * */
	public static void write(HttpServletResponse response, Map map, String contentType) throws Exception {
		response.setContentType(contentType);
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		String jsonMsg = gson.toJson(map);
		PrintWriter printWriter = response.getWriter();
		printWriter.write(jsonMsg);
		printWriter.close();
		System.out.println("ResultWriter.write()" +"/jasonMsg:" + jsonMsg);
	}
	
	/*2.response訊息設定,預設contentTyep ="application/json, charset=UTF-8"
	 * @param HttpServletResponse response => 1.Servlet的Response
	 * @param Map map => 2.要傳遞得訊息key:value
	 * */
	public static void write(HttpServletResponse response, Map map) throws Exception {
		write(response, map,defalutContentType);
	}
	
	/*3.response訊息OK
	 * @param HttpServletResponse response => 1.Servlet的Response
	 * */
	public static void writeOk(HttpServletResponse response) throws Exception {
		map.put("message", "OK");
		write(response, map,defalutContentType);
	}
	
	/*5.自動輸出code:200的
	 * @param HttpServletResponse response => 1.Servlet的Response
	 * */
	public static void writeCode(HttpServletResponse response) throws Exception{
		map.put("code", "200");
		write(response, map);
	}
	
	/*6.書寫Message:value,輸出response
	 *  @param HttpServletResponse response => 1.Servlet的Response
	 * 	@param String message => 2.要寫給使用者的訊息
	 * */
	public static void writeMsg(HttpServletResponse response , String message) throws Exception{
		map.put("message", message);
		write(response, map);
	}
	/*4.javaBeanToMap
	 * @param Object javabean => 要轉型成Map的JavaBean
	 * */
    public static Map<String, String> javaBeanToMap(Object javabean) {
        Gson gson = new Gson();
        String json = gson.toJson(javabean);
        Map<String, String> map = gson.fromJson(json, new TypeToken<Map<String, String>>() {
        }.getType());
        return map;
    }

}
