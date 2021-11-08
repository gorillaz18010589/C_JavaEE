package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.auth0.jwt.JWT;
import com.google.gson.Gson;

/**
 * Servlet implementation class text
 */
@WebServlet("/text")
public class text extends HttpServlet {
	
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doTask(req, resp);
		}
	
	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			Map<String, String> map = new HashMap<String, String>();
			map.put("hank","30");
			Gson gson = new Gson();
			gson.toJson(map);
			System.out.println(map);
			
			
	}

}
