package newtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//接Test_09,做輸出的事情就好
//這邊的request,respones,來自Ｔest_09,所以那邊已經處理好contentType設定的事情,這邊可以完全使用那邊的request
//這裡拿的是原來09的request,response的

@WebServlet("/Test_10_dispatcher")
public class Test_10_dispatcher extends HttpServlet {
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
		//輸出到網頁上
		out.print("<div>I am Hank10 Page </div>");
		
		//從Test_09,而來的request,respone
		String name = request.getParameter("name");
		String age = request.getParameter("age");

		out.print("我是被include近來的Test_10頁面 且request的name參數成功傳遞 name為:" + name +", age:" + age);
	
	}

	

}
