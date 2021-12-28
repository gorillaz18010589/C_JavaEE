package newtest;
//目的：看doGet,doPost怎麼叫進來的生命流程,取得html參數,還有一次取得所有參數方法
//目的：改流程不管你前端是get還是post我用doTask一樣都可接受流程一樣
//3:01:48
//html:Test1_To_Test_02.html
/*通常有在override的事先留著看一下他在做什麼事,不一定要在第一行,那是建構式才需要
 *service的流程其實是service(HttpServletRequest req, HttpServletResponse resp),先被呼叫在,叫爸super.service(req, resp);才會導向到doGet
 *service(HttpServletRequest req, HttpServletResponse resp)：其實就是將你收到hteml的doGet,或post透過Super判斷要傳給doGet(),還是Post();
 *網頁寫在webContent
 *網頁永遠是一種沒狀態的狀態,你每次叫回來都是獨立的事件,資料傳遞用表單
 *post不是你在url上面打的,他的資訊放在body裡
 *get:你從url上面打的永遠都是doget
 *後端程式的意義就是能夠處理user端的參數
 *user端發送過來的是跟我請求,requet,我送出去的輸出叫response
 *
 *form表單介紹:<form表單> 是跟使用者互動輸入資料,表單的重點是資料
 *form-> 表單沒寫action,自己這隻html處理,有寫交給你指定的處理
 *form:mame:欄位名稱 ,
 *type:輸入種類 ,
 * submit：資料送出,
 * value:明稱
 * action:這個表單交給誰處理,沒寫action代表自己處理,有可能是jsp自己處理
 * method:表單用什麼方式處理get/post等
 *submit按下送出後url改變成,且網頁輸入的資料重新載入
 *submit送出後會將參數傳到service,並且重新載入html,因為還未指定action
 *http://localhost:8080/message/Test1_To_Test_02.html?account=Hank&passowrd=123456
 *http://localhost:8080/message/Test1_To_Test_02.html -> html送出後還是交給我這隻Test1_To_Test_02處理
 *?account=Hank &passowrd=123456 -> ?欄位名稱 ＝ 使用者輸入的參數 &password欄位 ＝ 使用者輸入的密碼參數
 * html->form:加入action,指定哪個servlet來處理
 * <form action="Test_02_Parameter" method="post"><!-- action="交給哪隻servlet" -->
 
 /*
  * get url參數戴法
  * http://localhost:8080/message/Test_02_Parameter?    ＝> url
  * account=%26%2322823%3B%26%2323478%3B%26%2322909%3B& =>  我輸入帳號的大家好中文
  * passowrd=%26%2312555%3Bwsw                          ＝>密碼參數
  * 在url傳遞參數時,會進行編碼,像空白符號加號都會進行編碼
  * 
  *用Google瀏覽器輸入爪哇 圖片
  *
  *  => 變成 爪哇 ＋ 圖片 ：空白變成+號
  *  所以Google的空白是＋號
  *  網頁預設編碼為：iso-8859-1,如果不指定任何編碼的話,預設為iso-8859-1
  * */


 
//1.留doGet,doPost
//2.呼叫service(HttpServletRequest req, HttpServletResponse resp)
//3.doGet,doPost,service下log看流程
//4.一開始印出sevice(),再來才是doGet();
//5.其實是誤導的觀念,將service的super.service(req, resp);註解後,發現doGet沒被呼叫,代表是service的super導向到doGet的
//6.service的流程其實是service(HttpServletRequest req, HttpServletResponse resp),先被呼叫在,叫爸super.service(req, resp);才會導向到doGet.
//7.寫一個Test01.html
//8.寫了帳號密碼輸入的表單,發現都是由sevice這隻先取得,然後super爸爸看交給doGet還是doPost
//9.改流程新增一隻doTask,放在service裡面並且發揚光大,註解super會幫忙導向get,跟post,狀況,讓doGet,doPost,都倒到此
//

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Test_02_Parameter")
public class Test_02_Parameter extends HttpServlet {
	
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("service()");

		//5.super.service(req, resp):負責判斷是request方法是get還是post,並且導向到deGet或doPost
//		super.service(req, resp);//註解後讓他導向doTask()
		
		//10.將傳來的request,不管是get還是psot全部交給doTask來做,並將req,resp參數傳遞下去
		doTask(req,resp);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()");

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()");
	}
	
	//9.新增一隻doTask,放在service裡面並且發揚光大,註解super會幫忙導向get,跟post,狀況,讓doGet,doPost,都倒到此
	protected void doTask(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doTask()");
		
		//11.測試是否能不管,doGet,doPost都從這邊處理,並且成功輸出在網頁上
		response.setContentType("text/html;charset=GBK"); //GBK顯示在apple瀏覽器才不會亂碼,google是UTF-8
		PrintWriter printWriter = response.getWriter();
		
		//13.java才有的一次取得所有的參數,php還要一個欄位一個欄位對
		Enumeration<String> requestNames = request.getParameterNames();//取得所有參數的名稱
		while (requestNames.hasMoreElements()) {//當這些參數列舉裡面還有值時取處來
			String reqName = (String) requestNames.nextElement();//取得列舉裡面的參數名稱
			String requestValue = request.getParameter(reqName);//取得參數的值,沒有的話回傳空直
			printWriter.append(reqName + " = " + requestValue +"<br>");
		}
		
		//12.從html的user接收他們送出的request取得參數,一次一個
		String account = request.getParameter("account");//取得參數
		printWriter.write(account);
		
		printWriter.flush();
	
	}

}
