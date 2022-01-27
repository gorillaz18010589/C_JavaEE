package newtest;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

//目的將抓到的照片,存到webCotnent,upload底下
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
/**
 * Servlet implementation class Test_08_upload_folder
 */
@WebServlet("/Test_08_upload_folder")
public class Test_08_upload_folder extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		//1.取得掌管整個service相關的全局物件
		ServletContext servletContext = getServletContext();
		
		//2.取得初始化web.xml組太擋設定的參數,這邊uploadPath是我的name,value是我設定的資料夾位置
		String uploadPath = servletContext.getInitParameter("uploadPath");
		System.out.println(uploadPath); //
		
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
						new FileOutputStream(uploadPath +"/hank.jpg");
				fout.write(buf);
				fout.flush();
				fout.close();
	}

	

}
