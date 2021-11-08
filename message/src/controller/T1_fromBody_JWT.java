package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

/**
 * Servlet implementation class T1_fromBody_JWT
 */
@WebServlet("/T1_fromBody_JWT")
public class T1_fromBody_JWT extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req,resp);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		System.out.println("doTask()");
		
			
			response.setContentType("application/json, charset=UTF-8");
			PrintWriter printWriter = response.getWriter();
			
			String key ="key";
			Algorithm alg = Algorithm.HMAC256(key);
			Date currentTime = new Date();
			String jwt = JWT.create()
				.withIssuer("hank")
				.withSubject("userId")
				.withAudience("User")
				.withExpiresAt(new Date(currentTime.getTime() + 24*3600*1000L))
				.withJWTId("001")
				.withClaim("pulic msg", "hello")
				.sign(alg);
		
			System.out.println(jwt);	
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Properties properties = new Properties();
		
			properties.put("user", "root");
			properties.put("password","root");
			properties.put("serverTimezone","Asia/Taipei");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3307/iii", properties);
			
			
			String name = request.getParameter("name");
			String account = request.getParameter("account");
			String password = request.getParameter("password");
			String localeName = request.getLocalName();
			System.out.println("?ϥΪ̦???J:" +"name:" + name + "/account:" + account +"/password:" + password +"localeName:" + localeName);
			
			
			//?N???o?ѼƷs?W??DB
			String sql ="INSERT INTO user(name, account, password) VALUES(?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, account);
			preparedStatement.setString(3, password);
			int count = preparedStatement.executeUpdate();
			if(count == 1) {
			
//				//1. =>{"msg":["OK"]}
//				JSONObject jsonObject = new JSONObject();
//				jsonObject.append("msg", "OK");
//				printWriter.append(jsonObject.toString());
//				
//				//2.=>{"msg:OK"}
//				Gson gson = new Gson();
//				Map<String, String> map = new HashMap();
//				map.put("msg", "ok");
//				String jsonMsg = gson.toJson(map);
//				System.out.println(jsonMsg);
//				printWriter.write(jsonMsg);
				
				
				//3.?ۤv?g??API?Nresponse??msg??Json?榡?e?X
//				Map<String, String> map = new HashMap();
//				map.put("msg", "ok");
//				ResultWriter.write(response, map);
				
				//4.?ۤv?g???????ǰe{"msg":"OK"}??k
//				ResultWriter.writeOk(response);
				
				//5.
//				Map<String, String> map = new HashMap<>();
//				map.put("token", JwtUtils.createToken(name, account));
//				ResultWriter.write(response, map);
				
			}
			printWriter.flush();
//			System.out.println("DB insert???\:" + count +"??");
			
		}catch (Exception e) {
			System.out.println(e.toString());
		}
		

	}
}
