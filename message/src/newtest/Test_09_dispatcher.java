package newtest;
//https://www.youtube.com/watch?v=70PQZ41-E10 1:21:57
//目的：單一頁面走向流程,將這隻09利用調度員結合到test10,將參數傳遞且可以將10的頁面include近來
//算是為ＭＶＣ
//還可以將request,response,交給test_10,servlet去做處理
//這個直接走Get,url帶參數,不寫前端了
//這段最重要是從Request而來,輸出反而最單純
//Request.getRequestDispaceher,把他分到Ｔest_10 ex:RequestDispatcher dispatcher1 = request.getRequestDispatcher("Test_10_dispatcher");
///將test10,頁面include近來,並且將request,respone物件傳遞下去給Test_10去玩dispatcher1.include(request, response);
//好處之一：發現頁面原始碼根本沒寫道test_10的邏輯,
//有一些邏輯可以分配到其他servlet幫忙做
//也可以在getRequestDispatcher,不只可以加進servlet,也可用get傳遞參數	RequestDispatcher dispatcher1 = request.getRequestDispatcher("Test_10_dispatcher?age=18");
//可是還是會依你在10,最後寫的同一參數為主,如果同樣的參數以你在url的餐數為優先,如果沒有像age仍然可以取得
//2.再寫一個Servlet Test_10,來跟他搭配
//3.取得一個調度員將他分知道Ｔest_10做處理,同事用include將Ｔest_10頁面加進來,Request,Reponse同一物件傳遞給Ｔest_10繼續玩
//4.取得Request調度員物件，並將他分之到Test10 ex:RequestDispatcher dispatcher1 = request.getRequestDispatcher("Test_10_dispatcher");
//5.//5.將test10,頁面include近來,並且將request,respone物件傳遞下去給Test_10去玩 dispatcher1.include(request, response);
//6.在Ｔest_10,拿到09的requse物件就去getName參數,顯示
//7.getRequestDispatcher,不只可以加進servlet,也可用get傳遞參數	RequestDispatcher dispatcher1 = request.getRequestDispatcher("Test_10_dispatcher?age=18");
/*
 * 介紹分支行為
 * 分支行為：人家會來問9號,9號會把一部分事情交給10號處裡,但是最後還是9號回應
 * */
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/Test_09_dispatcher")
public class Test_09_dispatcher extends HttpServlet {
       
   
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// ?name== xxx => doGet ,//這邊直接用get url帶參數,取得name
		String name = request.getParameter("name");
		
		//4.取得Request調度員物件，並將他分之到Test10
//		RequestDispatcher dispatcher1 = request.getRequestDispatcher("Test_10_dispatcher");
		
		//7.getRequestDispatcher,不只可以加進servlet,也可用get傳遞參數
		RequestDispatcher dispatcher1 = request.getRequestDispatcher("Test_10_dispatcher?age=18");

		
		//這個一定要一開始設定
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//輸出到網頁上
		out.print("<h1>Hank 大公司</h1>");
		out.print("<hr>");
		out.print("I am " + name);
		out.print("<hr>");

		
		//5.將test10,頁面include近來,並且將request,respone物件傳遞下去給Test_10去玩
		dispatcher1.include(request, response);
		
		
	}

	

}
