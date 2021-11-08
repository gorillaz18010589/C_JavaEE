package text;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.bcel.internal.generic.D2F;

import model.DB;
/**
 * Servlet implementation class T5_Select_指定欄位
 */
@WebServlet("/T5_Select_指定欄位")
public class T5_Select_指定欄位 extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doTask(req, resp);
	}
	
	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
			DB db = new DB(response);
		    db.changeActive("fff@gmail.com", "08b2abd7cb3bf01e39b54146e826f0a3");
		
	}
}
