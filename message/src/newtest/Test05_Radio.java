package newtest;

import java.io.IOException;
//
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//javax.servlet.ServletRequest.getParameterValues(String name):多選題時可用,取得多個參數時(回傳String[])
//java.io.PrintWriter.write(String s):輸出串流檔案(void)
//test04.html
//目的html等input取得參數方法,二選一,多選,郵遞區號下拉選單,文字選單
//
//
//後端要能收得到給name這些都是後端再給的
/*
 * //1.抓取男女二選一方式
		if(gender.equals("f")) {
			printWriter.append("女生");
		}else {
			printWriter.append("男生");
		}
 * 
 *  //3.多選題利用getParameterValues,一次取得同一個參數的所有值
		 String[] hobbys = request.getParameterValues("hobby");
		 for(String hobbyValue : hobbys) {
				printWriter.append("選到：" + hobbyValue +"<br>");
		 }
 * 
 * //4.下拉式選單取得郵遞區號方式,我知道你真正想要的是郵遞區號,所以value 401,402,403,404,直接把你想要的值放在vlaue中
		 String  addressValue = request.getParameter("address");
		 printWriter.append(addressValue);
 * 
 * 
 *  //5.取得textarea參數內容
		 String text = request.getParameter("text");
		 printWriter.append(text);
		 printWriter.append("<br>");
 * */
//
/**
 * Servlet implementation class Test05_Radio
 * 示範二選一取得參數
 * 多選取得參數
 * <!-- name="gender" 兩個參數一樣群組化  box可以改選-->
<!--value="m" submit後代表的值 /Test05_Radio?gender=m-->
<!-- checked 預設圈選 -->
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
		
		//1.抓取男女二選一方式
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
		 
		 //4.下拉式選單取得郵遞區號方式,我知道你真正想要的是郵遞區號,所以value 401,402,403,404,直接把你想要的值放在vlaue中
		 String  addressValue = request.getParameter("address");
		 printWriter.append(addressValue);
		 printWriter.append("<br>");

		 
		 //5.取得textarea參數內容
		 String text = request.getParameter("text");
		 printWriter.append(text);
		 printWriter.append("<br>");


	}


	

}
