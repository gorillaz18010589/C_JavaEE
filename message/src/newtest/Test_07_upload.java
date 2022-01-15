package newtest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

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
//https://www.youtube.com/watch?v=70PQZ41-E10 //10:17
//＊檔案上傳不會透過url的方式
//*檔案上傳是用post放在body裡
//*java自己就是伺服器所以必須處理檔案上傳上來這件事,php則不用,java要因為上來是由service處理
//*在網頁裡面只要有upload其他上傳方式,只能用post不能用get
//*其中一個原因是因為url的長度,根本沒有限制,但通訊瀏覽器有各自的長度限定，像google,火狐各自有,但就其實是有限制
//*宣告@MultipartConfig才能處理檔案上傳取得headerValue
//getInputStraem,串流取得檔案,io串流取得檔案
//玩系統要注意後端jsp,php,servlet,.net這些後端程式,擴及到整個伺服器沒有完好的話，整個作業系統會毀掉那很正常
//
//
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
  * */
 /*
 * 檔案上傳html一定要加上編碼方法 :
 * enctype="mlutpart/from-data">
 * enctype:編碼方式
 * mlutpart：會把東西切成很多段的意思
 * from-data：切表單的資料
 * 這個表單會被切成很多段編碼的方式
 * 這個一定要帶到,不然後端會收不到
 * */
@WebServlet("/Test_07_upload")
//D.要宣告才能取的檔案的header資料
@MultipartConfig
public class Test_07_upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
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
		
		//F.將檔案輸出到指定目錄
		FileOutputStream fout =
				new FileOutputStream("/Users/jennifer/Desktop/java_ee/hank.jpg");
		fout.write(buf);
		fout.flush();
		fout.close();
	
	}

}
