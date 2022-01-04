package newtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//接test03.html,目的取得參數相加,數字轉型,跟如果使用者亂輸入的處理
//https://www.youtube.com/watch?v=j1buJD0I0MI 1:08:54
/*
 * 網頁來的東西永遠是字串
 * 你js抓出來的也是字串要是網頁的任何地方都是字串java,php都是
 * 從網頁字串取得參數後轉型成int去做運算,運算的東西加try catch,做處理
 * */
/**
 * Servlet implementation class Test_04_ParAdd
 */
@WebServlet("/Test_04_ParAdd")
public class Test_04_ParAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1.看一下網頁輸入數字,這邊取得log是多少
		String x = request.getParameter("x");
		String y = request.getParameter("y");
		System.out.println(x +";" + y);//3;5
		
		//2.從網頁字串取得參數後轉型成int去做運算,運算的東西加try catch
		int intX, intY; 
		 try {
			 intX = Integer.parseInt(x);
			 intY = Integer.parseInt(y);
			
		 }catch (Exception e) {
		 //3.如果沒有值我們讓他預設為0,防止別人來亂
			 intX = intY = 0;
			 System.out.println();
		}
		 int intResult  = intX + intY;
		 System.out.println(intResult);
		 
		 response.setContentType("text/html; charset=UTF-8"); 
		 PrintWriter writer = response.getWriter();
		 writer.append(intX + "+" + intY + "=" + intResult);
	}


}
