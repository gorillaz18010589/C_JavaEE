package newtest;
/*影片：2019/06/03 ->02 ,2:08:54
 * 目的：介紹Ｈeader,取得個瀏覽器Header的方法,即Ｈeader介紹
 * servlet本身也也很多隱含物件,像Js的Doumnent根本沒有New過卻可以使用
 * hader:擋頭每個瀏覽器傳過來的header會有差異
 * header:先從User端要求的Request的Hader來看
 * 後端的目的：是負責送頁面原始碼過去
 * 後端的好處：是我們取得資訊後，由writer送出,code不會被複製,而前端可以被看到頁面原始碼
 * 後端的目的：撈資料送用resopnse物件printWrite串流送出去
 * 特性：每個瀏覽器傳遞過來的header會有差異
 * 1.Enumeration<String> request.getHeaderNames()取得人家提出要求的HeaderNames
 * 2.java.util.Enumeration -> 查看api只有兩招,一招問還有沒有泛型資料,有的話拿出來
 * */

/*後端該做的處理
 * 目的：用Yahoo賣家上傳圖片,每個人圖片大小格式不同,
 * 所以伺服器在接收到圖片時,馬上做格式的處理跟調整,才會讓使用者上傳的照片格式一樣
 * 
 * */

/*header屬性值介紹
 * 
 *host=localhost:8080         ->要求的主機是
 *upgrade-insecure-requests=1
 *accept=text/html,application/xhtml+xml,application/xml;q=0.9,;q=0.8
 *user-agent=Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/14.0.2 Safari/605.1.15
 *accept-language=zh-tw    ->預設的語系(zh代表中文語系 - tw代表香港地區)
 *accept-encoding=gzip, deflate
 *connection=keep-alive    ->連現狀態
*/
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;import com.mysql.cj.util.DnsSrv.SrvRecord;

@WebServlet("/Test01_Header")
public class Test01_Header extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//4.要輸出網頁,設定類別,取得PrintWriter
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter printWriter = response.getWriter();
		
		//1.取得人家提出要求的HeaderNames
		Enumeration<String> headeNames = request.getHeaderNames();
		
		//2.當headeNames,還有下一個值時繼續拿出下一個物件,取得headeName
		while(headeNames.hasMoreElements()) {	
		String headeName = headeNames.nextElement(); //取得HeadeName
		
		//6.有了headName就可以取其值
		String headerValue = request.getHeader(headeName);
		
		//7.印出headeName , headeValue,並且換行
		printWriter.write(headeName +" = "+  headerValue +"<br>");
		}
	
		//5.printWriter也是一種輸出串流記得衝出來flush
		printWriter.flush();
	}

	

}
