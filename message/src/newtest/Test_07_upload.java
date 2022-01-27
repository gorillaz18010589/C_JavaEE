package newtest;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.crypto.KeySelector.Purpose;

import javafx.geometry.Side;
//目的：檔案上傳
//https://www.youtube.com/watch?v=70PQZ41-E10 56:05

//＊檔案上傳不會透過url的方式
//*檔案上傳是用post放在body裡
//*java自己就是伺服器所以必須處理檔案上傳上來這件事,php則不用,java要因為上來是由service處理
//*在網頁裡面只要有upload其他上傳方式,只能用post不能用get
//*其中一個原因是因為url的長度,根本沒有限制,但通訊瀏覽器有各自的長度限定，像google,火狐各自有,但就其實是有限制
//*宣告@MultipartConfig才能處理檔案上傳取得headerValue
//getInputStraem,串流取得檔案,io串流取得檔案
//玩系統要注意後端jsp,php,servlet,.net這些後端程式,擴及到整個伺服器沒有完好的話，整個作業系統會毀掉那很正常
//上傳的地方通常設定在WEB-INF底下,會有一個規劃
//web.xml：組太黨,裡面設定標籤content -> 類似安著applcatContent
//Ｇ,H目的將抓到的照片,存到webCotnent,upload底下
//web.xml組太黨：
//為什麼要這樣寫跟部署伺服器有關,因為路徑可以直接在web.xml調整,讓他調到正確的位置,程式不用改
//因為我門是編譯的程式,所以盡量用組太黨web.xml去改,不要動到程式
//java後端效能會比其他好,因為我們是編譯之後
//javax.servlet.http.Part.getSize()://取得大小(回傳long)
//javax.servlet.http.Part.getName()://取得你前端參數的設定的name值(回傳String)
//javax.servlet.http.Part.getSubmittedFileName()://取得你送出的檔案名稱(回傳String)
//javax.servlet.GenericServlet.getServletContext()://取得一個掌管伺服器全局的物件(回傳ServletContext)
//javax.servlet.ServletContext.getInitParameter(String arg0)://這邊是取得你在web.xml,content底下設定的參數值取得web.xml的上傳路徑(回傳String)
/*
 * Collection介紹
 * 1.只要是Collection都可以for each
 * 2.set,list都是所以都可以for each
 * 3.因為他們是Collection
 * 4.出現Unable to process parts as no multi-part configuration has been provided bug
 * */
/*
 /*流程
  * //A.接收part,也就是html的multipart,你剛給的參數名稱
	//B.取得檔案上傳所有的header值
  * //C.取得所有的header出現Unable to process parts as no multi-part configuration has been provided
  * //D.要宣告@MultipartConfig才能取的檔案的header資料
  *   E.取得的header資訊
  *   content-disposition:form-data; //html檔案上傳格式
  *   name="upload"; 				 //html參數名稱
  *   filename="9月薪資.png"			 //檔案名稱
  *   content-type:image/png		 //檔案型別
  *   //F.將檔案輸出到指定目錄
  *   //G.在web.xml底下設定常數,引用標籤
  *   //H.webContent -> folder ->upload資料夾,將下載好的圖片放到那邊,可是那邊是哪邊
  *   //I.<?xml version="1.0" encoding="UTF-8"?>
<web-app 
		 xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
         
         <context-param>
         	<param-name>uploadPath</param-name>
         	<param-value>/Users/jennifer/git/javaee/message/WebContent/upload</param-value> //檔案放在這,
         </context-param>
</web-app>
  * */
 /*
 * 檔案上傳html一定要加上編碼方法 :
 * enctype="mlutpart/from-data">
 * enctype:編碼方式
 * mlutpart：會把東西切成很多段的意思
 * from-data：切表單的資料
 * 這個表單會被切成很多段編碼的方式
 * 這個一定要帶到,不然後端會收不到
 * 檔案上傳通常設置在Ｗeb-Xml底下
 * 搜尋https://tomcat.apache.org/tomcat-7.0-doc/appdev/web.xml.txt
 * 講檔案檔名改成
 * 檔案存放在web.xml定義的常數下
 * 檔案存放適用絕對路徑因為這樣專案移轉比較好轉,而不使用相對目錄
 * 
 * */

//Ｇ,H目的將抓到的照片,存到webCotnent,upload底下
//web.xml組太黨：
//為什麼要這樣寫跟部署伺服器有關,因為路徑可以直接在web.xml調整,讓他調到正確的位置,程式不用改
//因為我門是編譯的程式,所以盡量用組太黨web.xml去改,不要動到程式
//java後端效能會比其他好,因為我們是編譯之後
/*
* 組太黨,
* <context-param> content -> 類似appcation
       	<param-name>uploadPath</param-name>
       	<param-value>/Users/jennifer/git/javaee/message/WebContent/upload</param-value>
       </context-param>
* */
@WebServlet("/Test_07_upload")
//D.要宣告才能取的檔案的header資料
@MultipartConfig
public class Test_07_upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		//G.將照片的路徑用組太黨web.xml,存在content裡,並且取得路徑
		//1.取得掌管整個service相關的全局物件
		ServletContext servletContext = getServletContext();
		
		//2.取得初始化web.xml組太擋設定的參數,這邊uploadPath是我的name,value是我設定的資料夾位置
		String uploadPath = servletContext.getInitParameter("uploadPath");
		
		//A.接收part,也就是html的multipart,你剛給的參數名稱
		Part partUpload = request.getPart("upload");
		
		//B.取得檔案上傳所有的header值
		Collection<String> names = partUpload.getHeaderNames();
		for(String headetName :names) {
			String headerValue =  partUpload.getHeader(headetName);
			System.out.println(headetName +":" + headerValue);
		}
		
		String partName = partUpload.getName();//取得參數名稱
		System.out.println("partName:" + partName); //partName:upload
		
		long uploadSize ;
		uploadSize = partUpload.getSize();//取得檔案byte大小
		System.out.println("uploadSize -> getSize()" + uploadSize);//getSize()152465

		String submittedFileName = partUpload.getSubmittedFileName();//取得上傳的檔案名稱
		System.out.println("submittedFileName:"+ submittedFileName);//submittedFileName:投履歷1.png

		//E.取得檔案串流
		BufferedInputStream bin =
				new BufferedInputStream(partUpload.getInputStream());
		byte[] buf = new byte[(int) uploadSize];//準備一個這個黨大小的buf byte[],以便讀取
		bin.read(buf);	//讀取檔案串流
		bin.close();	//關閉串流
		
		
		//F.將檔案輸出到指定目錄,此招為寫死,G,H有新的步驟
//		FileOutputStream fout =
//				new FileOutputStream("/Users/jennifer/Desktop/java_ee/hank.jpg");
//		fout.write(buf);
//		fout.flush();
//		fout.close();
		
		//H.新招將抓取的檔案存放到設定好的組太黨路徑底下,好處是以後改位置到web.xml底下去改就好,程式不變
		File uploadFile = new File(uploadPath,submittedFileName);
		
		FileOutputStream fout =
				new FileOutputStream(uploadFile);
		fout.write(buf);
		fout.flush();
		fout.close();

	
	}

}
