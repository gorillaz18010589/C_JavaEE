package newtest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//目的：檔案上傳
//https://www.youtube.com/watch?v=j1buJD0I0MI,2:28:44
//＊檔案上傳不會透過url的方式
//*檔案上傳是用post放在body裡
//*java自己就是伺服器所以必須處理檔案上傳上來這件事,php則不用,因為上來是由service處理
//*在網頁裡面只要有upload其他上傳方式,只能用post不能用get
//*其中一個原因是因為url的長度,根本沒有限制,但通訊瀏覽器有各自的長度限定，像google,火狐各自有,但就其實是有限制
//
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
public class Test_07_upload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Test_07_upload() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
