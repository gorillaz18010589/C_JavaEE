package newtest;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//javax.servlet.ServletRequest.getParameterValues(String name):多選題時可用,取得多個參數時(回傳String[])
//java.io.PrintWriter.write(String s):輸出串流檔案(void)
/**
 * Servlet implementation class Test05_Radio
 */
@WebServlet("/Test05_Radio")
public class Test05_Radio extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gender = request.getParameter("gender");
		
		response.setContentType("text/html; charset=UTF-8");//設定標籤內容,網頁html
		PrintWriter printWriter = response.getWriter();
		
		//抓取男女二選一方式
		if(gender.equals("f")) {
			printWriter.append("女生");
		}else {
			printWriter.append("男生");
		}
			
	

		 //2.取得打系列多選參數,只要多選參數,參數仍然只會抓取以第一個為主(此方法無法多選參數)
//		 String hobby = request.getParameter("hobby");
//		 int hobbyInt = Integer.parseInt(hobby);
//		 switch (hobbyInt) {
//		case 0:
//			printWriter.append("打籃球");
//			break;
//		case 1:
//			printWriter.append("打毛線");
//			break;
//		case 2:
//			printWriter.append("打羽球");
//			break;
//		case 3:
//			printWriter.append("打高爾夫球");
//			break;
//		case 4:
//			printWriter.append("打氣球");
//			break;
//		case 5:
//			printWriter.append("打人");
//			break;
//		
//
//		default:
//			break;
//		}
		 
		 //3.多選題利用getParameterValues,一次取得同一個參數的所有值
		 String[] hobbys = request.getParameterValues("hobby");
		 for(String hobbyValue : hobbys) {
				printWriter.append("選到：" + hobbyValue +"<br>");
		 }

	}


	

}
