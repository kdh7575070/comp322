package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import entity.Account;

public class AccountService {
	private static String url = "jdbc:postgresql://localhost/testdb";
	private static String uid = "testdb";
	private static String pwd = "testdb";
	private static String driver = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost/knumovie";
	private static String uid = "postgres";
	private static String pwd = "comp322";
	private static String driver = "org.postgresql.Driver";
			
	public static List<Account> getList() throws ClassNotFoundException, SQLException {
		String sql = "SELECT * FROM Account";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);
		
		List<Account> list = new ArrayList<Account>();
	
		
		while(rs.next()) {
			String User_id = rs.getString("User_id");
			String Password = rs.getString("Password");
			String First_name = rs.getString("First_name");
			String Last_name = rs.getString("Last_name");
			String Phone_number = rs.getString("Phone_number");
			Date Birthday = rs.getDate("Birthday");
			String Sex = rs.getString("Sex");
			String Address = rs.getString("Address");
			String Job = rs.getString("Job");
			String Membership_status = rs.getString("Membership_status");
			boolean Is_admin = rs.getBoolean("Is_admin");

			Account account = new Account(
					User_id,
					Password,
					First_name,
					Last_name,
					Phone_number,
					Birthday,
					Sex,
					Address,
					Job,
					Membership_status,
					Is_admin
					);
			list.add(account);
			
		}
		
		 return list;
	}
	
	public static int create_account(Account account) throws ClassNotFoundException, SQLException {
		String User_id = account.getUser_id();
		String Password = account.getPassword();
		String First_name = account.getFirst_name();
		String Last_name = account.getLast_name();
		String Phone_number = account.getPhone_number();
		Date Birthday = account.getBirthday();
		String Sex = account.getSex();
		String Address = account.getAddress();
		String Job = account.getJob();
		String Membership_status = account.getMembership_status();
		boolean Is_admin = account.getIs_admin();
		
		
		String sql = "INSERT INTO Account ( "
				+ "		User_id,"
				+ "		Password,"
				+ "		First_name,"
				+ "		Last_name,"
				+ "		Phone_number,"
				+ "		birthday,"
				+ "		Sex,"
				+ "		Address,"
				+ "		Job,"
				+ "		Membership_status,"
				+ "		Is_admin"
				+ ") VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, User_id);
		st.setString(2, Password);
		st.setString(3, First_name);
		st.setString(4, Last_name);
		st.setString(5, Phone_number);
		st.setDate(6, Birthday);
		st.setString(7, Sex);
		st.setString(8, Address);
		st.setString(9, Job);
		st.setString(10, Membership_status);
		st.setBoolean(11, Is_admin);
		
		int rs = st.executeUpdate();
		return rs;
	}
	
	public static int login(String User_id, String Password) throws ClassNotFoundException, SQLException {
		
		String sql = "SELECT Password FROM Account WHERE User_id = ?";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);

		PreparedStatement st = con.prepareStatement(sql);
		
		// pstmt : prepared statement 정해진 sql문장을 db에 삽입하는 형식으로 인스턴스가져옴
		st = con.prepareStatement(sql);
	
		// 물음표해당하는 내용을 유저아이디로, 매개변수로 이용.. 1)존재하는지 2)비밀번호무엇인지
		st.setString(1, User_id);
	
		ResultSet rs = st.executeQuery();
	
		// 결과가 존재한다면 실행
		if (rs.next()) {
			
			// 패스워드 일치한다면 실행
			if (rs.getString(1).equals(Password)) {	
				System.out.println("password is " + Password);
				return 1; // 성공
			}
			else {
				return 0; // 비밀번호 불일치
			}
			
		}
		return -1; // 아이디가 없음 오류
	}
	
	public static void delete_account(String User_id) throws ClassNotFoundException, SQLException {
		String sql_check_admin = "SELECT user_id FROM Account WHERE is_admin=True"; //view로 대체..
		String sql = "DELETE  FROM Account WHERE user_id=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql_check_admin);
		ArrayList<String> admin_names = new ArrayList<String>();
		while (rs.next()) {
			admin_names.add(rs.getString(1));
		}
		System.out.println(admin_names.size());
		
		if(admin_names.size() <= 1) {
			for(String name :admin_names) {
				if(name.equals(User_id)) {
					System.out.println("관리자 계정은 반드시 한개이상 존재해야합니다.");
					rs.close();
					stmt.close();
					con.close();
					return;
				}
			}
		}
				
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, User_id);
		
		int result = st.executeUpdate();
		if (result == 1) {
			System.out.println("Deleted Successfully");
		} else {
			System.out.print("Wrong ID");
		}
		
		st.close();
		con.close();
	}
}


//	
//	public int update(Account account) throws ClassNotFoundException, SQLException {
//		String title = notice.getTitle();
//		String content = notice.getContent();
//		String files = notice.getFiles();
//		int id = notice.getId();
//		
//		String sql = "UPDATE Notice "
//				+ "SET"
//				+ "		title=?,"
//				+ "		content=?,"
//				+ "		files=?,"
//				+ "WHERE ID=?";
//		
//		Class.forName(driver);
//		Connection con = DriverManager.getConnection(url,uid,pwd);
//		PreparedStatement st = con.prepareStatement(sql);
//		st.setString(1, title);
//		st.setString(2, content);
//		st.setString(3, files);
//		st.setInt(4, id);
//		
//		int rs = st.executeUpdate();
//		
//		st.close();
//		con.close();
//		
//		return rs;
//	}
//	
//}
