package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.digest.DigestUtils;

import beans.GoogleSiginAccoutBean;
import beans.UserBean;
import maill.MailUtils;

public class DB {

	private final static String USER ="root";
	private final static String PASS_WORD ="root";
	private final static String JDBC ="com.mysql.cj.jdbc.Driver";
	private final static String DB_NAME ="text";
	private final static String SERVER_TIMEZONE ="?serverTimezone=Asia/Taipei";
	private final static String URL ="jdbc:mysql://localhost:3307/" + DB_NAME + SERVER_TIMEZONE;
	private Connection con;
	
	HttpServletResponse response;
	
	public DB(HttpServletResponse response) {
		this.response = response;
	}
	
	
	public void doConnect() {
		try {
			Class.forName(JDBC);
			con = DriverManager.getConnection(URL, USER, PASS_WORD);
			System.out.println("doConnect():成功");
		}catch (Exception e) {
			System.out.println("doConnect()失敗:" + e.toString());
		}	
	}
	
	public void doClose() {
		try {
			con.close();
			System.out.println("doClose():成功");
		} catch (Exception e) {
			System.out.println("doClose()失敗:" + e.toString());
		}
	}
	
	//A.會員註冊
	public String addUser(UserBean user) {
		String sql ="INSERT INTO user(name,account,password,hash,active)VALUES(?,?,?,?,?)";
		doConnect();
		String registerMsg = "";
		try {
		
			String name = user.getName();
			String account = user.getAccount();
			String password = user.getPassword();
			
			//產生專屬使用的hashCode
			String hash;
			Random random = new Random();
			random.nextInt(999999);
			System.out.println(random.toString());
			hash = DigestUtils.md5Hex("" + random);
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getAccount());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, hash);
			pstmt.setInt(5, user.getActive());
			
			int i = pstmt.executeUpdate();
			if( i != 0) {
				System.out.println("addUser()成功:=>" + user.toString());
				ResultWriter.writeCode(response);
				ResultWriter.writeOk(response);
				MailUtils.getInstance().sendRegisterEmail(account, hash);
				registerMsg = "success";
			}
			doClose();


		}catch (Exception e) {
			
			System.out.println("addUser()失敗:" + e.toString());

		}
		
		return "success";

	}
	
	public void addGoogleSiginAccount(GoogleSiginAccoutBean googleSiginAccoutBean) {
		doConnect();
		String sql ="INSERT INTO google_signin_account(wid,returna,noun,description,par,type,clazz,extend) VALUES (?,?,?,?,?,?,?,?)";
		
		String wid = googleSiginAccoutBean.getwId();
		String returna = googleSiginAccoutBean.getReturna();
		String noun = googleSiginAccoutBean.getNoun();
		String description = googleSiginAccoutBean.getDescription();
		String par = googleSiginAccoutBean.getPar();
		String type = googleSiginAccoutBean.getType();
		String clazz = googleSiginAccoutBean.getClazz();
		String extend = googleSiginAccoutBean.getExtend();
		
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, wid);
			pstmt.setString(2, returna);
			pstmt.setString(3, noun);
			pstmt.setString(4, description);
			pstmt.setString(5, par);
			pstmt.setString(6,type);
			pstmt.setString(7,clazz);
			pstmt.setString(8, extend);
			
			int i = pstmt.executeUpdate();
			if(i != 0) {
				System.out.println("addGoogleSiginAccount()成功新增:" + i +"資料");
				ResultWriter.writeCode(response);
				doClose();
			}

		}catch (Exception e) {
			System.out.println("addGoogleSiginAccount()失敗:" + e.toString());
		}

	}
	
	//C.查詢是否激活,激活active,改成active改成0
	public int changeActive(String account, String hash)  {
		
			int i = 0;
			doConnect();
			String sql ="SELECT account,hash,active FROM user WHERE account = ? AND hash = ? AND active ='0'";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, account);
			pstmt.setString(2, hash);
			ResultSet rs = pstmt.executeQuery();
				if(rs.next()) {
					//2.把激活狀態更改,activie改為1,email跟hash瑪也更新
					String sql1="UPDATE user SET active='1' where account = ? AND hash = ?";
					PreparedStatement pstmt1 = con.prepareStatement(sql1);
					pstmt1.setString(1, account);
					pstmt1.setString(2, hash);
					System.out.println("changeActive成功():激活狀態");
					 i = pstmt1.executeUpdate();		
				}else 
				{
					System.out.println("changeActive()失敗");
				}		
				
		}catch (Exception e) {
				System.out.println("changeActive()錯誤:" + e.toString());
			}
		return i;
			
	}
	
	//insert token 進入user
	public void addToken (String account, String hash, String token) {
		doConnect();
		String querySql = "INSERT INTO user (account,hash,token) SELECT account,hash,token FROM user";
		 try {
			PreparedStatement pstmt = con.prepareStatement(querySql);
			pstmt.setString(1, account);
			pstmt.setString(2, hash);
		    pstmt.setString(3, token);
			int i = pstmt.executeUpdate();
			System.out.println("addToken()成功:" + token);

		} catch (SQLException e) {
			System.out.println("addToken()失敗:" + e.toString());

		}
	}
	
}
