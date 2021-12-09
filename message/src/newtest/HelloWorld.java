package newtest;
//JavaEE第一支介紹
//註解清掉乾乾淨淨來介紹
//1.@WebServlet在外面你的User端存取是用這個方式 ("/HelloWorld") -> 對外營業的名稱
//2.類別永遠是繼承HttpServlet的這才是service類別
//4.get跟post是對網站提出要求的兩種非常見得模式
//＊看java servlet api -> https://tomcat.apache.org/tomcat-5.5-doc/servletapi/
//*javax.servlet.http 找到package查api
//要找get writer 發現javax.servlet.http Interface HttpServletResponse 累裡面沒有,而是父街口有javax.servlet.ServletResponse實作取得
//點下去get writer 發現public java.io.PrintWriter getWriter() 有用到java.io的api
//*重啟伺服器是一條很大條的事情不要亂重啟,假設有人正在寶物交易,如果重啟那你麻煩了,伺服器盡量不要重啟,我們會送一個差段訊號給他,叫他重新組態黨
//*內外瀏覽器請以外部為主
//9.servlet建構式意義不大：servlet物件開始後,就固定用這個物件服務大眾,所以建構式只有service第一開創件時執行,所以這邊建構式跟static意義不大
//9.創建物件後就由這個servlet服務大眾,重新整理網頁5次 doGet ->跑了5次,但建構式只有重啟服務那次創建後,就由他同一為大家服務
//10.示範static 在servlet意義不大的事情,本來servlet可以被多個物件擁有,但現在只有這物件一直服務,重新整理7次

//doGet基本介紹:你的用戶端從url輸入提出請求,大部分會使用doGet
//doGet不是我呼叫的方法,他會傳遞兩個重要參數近來request(對方提出的請求),response(你會回應的物件）
//doGet做什麼事：拿人家的request請求,處理,做出response回應


//doPost:會將相關的訊息參數包在body裡面做傳遞,兩種特性(1).資訊隱藏就看你要用doGet還是Post （2).檔案上傳是一定要用doPost

//PrintWriter append(CharSequence csq):append再加上有實作CharSequence的物件回傳自己PrintWriter
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet在外面你的User端存取是用這個方式 ("/HelloWorld") -> 對外營業的名稱
@WebServlet("/HelloWorld") 
public class HelloWorld extends HttpServlet {
	private int a = 0;
	private static int b = 0;
	//3.建構是物件初始化,只是在servlet沒有那麼重要
    public HelloWorld() {
        super();
        System.out.println("HelloWorld()");
    }
    
    //6.doGet不是我呼叫的方法,他會傳遞兩個重要參數近來request(對方提出的請求),response(你會回應的物件）
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    	//8.設定為html類別後.append("<h1> hellow world </h1>") 就能成功顯示為大寫Hello world
    	response.setContentType("text/html; charset=UTF-8");
		response.getWriter()
		.append("Served at: ")
		.append(request.getContextPath())
		.append("hello world")
		
	//7.示範加上<h1>標籤瀏覽器卻沒有變成html,原因是我們送出的資料並沒有用html格式,所以瀏覽器看待他為一般的文字內容
		.append("<h1> hellow world </h1>");//<h1> hellow world </h1>
		
		//9.示範doGet再重新整理網頁後會被呼叫,而建構式只有重啟服務那次時創建,之後統一由這個service來服務大眾
		System.out.println("doGet()");
		
		//10.示範static 在servlet意義不大的事情,本來servlet可以被多個物件擁有,但現在只有這物件一直服務,重新整理7次
		a++; b++;
		System.out.println("a:" +a  + ", b" + b); //a:7, b7
	}
	
	//5.目前簡單不介紹doPost所以暫時註解清楚介紹其他doGet
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		doGet(request, response);
//	}

}
