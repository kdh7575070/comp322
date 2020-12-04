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
	private static String uid = "taeha";
	private static String pwd = "testdb";
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
			int Job = rs.getInt("Job");
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
					Is_admin
					);
			list.add(account);
			
		}
		System.out.print("0. first user is : ");
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
		int Job = account.getJob();
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
				+ "		Is_admin"
				+ ") VALUES (?,?,?,?,?,?,?,?,?,?)";
		
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
		st.setInt(9, Job);
		st.setBoolean(10, Is_admin);
		
		int rs = st.executeUpdate();
		System.out.println("User created successfully");
		return 1;		
	}
	
	public static String login(String User_id, String Password) throws ClassNotFoundException, SQLException {
		
		String sql = "SELECT Password FROM Account WHERE User_id = ?";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);

		PreparedStatement st = con.prepareStatement(sql);
		st = con.prepareStatement(sql);
	
		st.setString(1, User_id);
		ResultSet rs = st.executeQuery();
	
		if (rs.next()) {
			
			if (rs.getString(1).equals(Password)) {	
				System.out.println("1D. Log in successful");
				return User_id;
			}
			else {
				System.out.println("1D. Password is wrong");
				return "";
			}
			
		}
		System.out.println("1D. No such id like that");
		return "";
	}
	
	public static void delete_account(String User_id) throws ClassNotFoundException, SQLException {
		String sql_check_admin = "SELECT user_id FROM Account WHERE is_admin=True"; //view로 대체..
		String sql = "DELETE FROM Account WHERE user_id=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql_check_admin);
		ArrayList<String> admin_names = new ArrayList<String>();
		while (rs.next()) {
			admin_names.add(rs.getString(1));
		}
		
		if(admin_names.size() <= 1) {
			for(String name :admin_names) {
				if(name.equals(User_id)) {
					System.out.println("1E. At least one admin must exist.");
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
			System.out.println("1E. Deleted successfully");
		} else {
			System.out.println("1E. Delete was unsuccessful");
		}
		st.close();
		con.close();
	}
	
	public static int update_user_info(Account new_info) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Account "
				+ "SET"
				+ "		First_name=?,"
				+ "		Last_name=?,"
				+ "		Phone_number=?,"
				+ "		Birthday=?,"
				+ "		Sex=?,"
				+ "		Address=?,"
				+ "		Job=?,"
				+ "		Membership_status=?"
				+ "   WHERE user_id=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, new_info.getFirst_name());
		st.setString(2, new_info.getLast_name());
		st.setString(3, new_info.getPhone_number());
		st.setDate(4, new_info.getBirthday());
		st.setString(5, new_info.getSex());
		st.setString(6, new_info.getAddress());
		st.setInt(7, new_info.getJob());
		st.setString(8, new_info.getMembership_status());
		st.setString(9, new_info.getUser_id());
		
		int rs = st.executeUpdate();
		if (rs == 1) System.out.println("1B. Update Successfully");
		st.close();
		con.close();
		
		return rs;
	}	
	
	public static int update_user_pwd(String loginuser, String new_pwd) throws ClassNotFoundException, SQLException {
		String sql = "UPDATE Account "
				+ "SET"
				+ "		Password=?"
				+ "   WHERE user_id=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, new_pwd);
		st.setString(2, loginuser);
		
		int rs = st.executeUpdate();
		if (rs == 1) System.out.println("1C. Change Password Successfully");
		st.close();
		con.close();
		
		return rs;
	}
	
	public static void check_my_ratinglist(String loginuser) throws ClassNotFoundException, SQLException {
		
		String sql = "SELECT movie_title, ratings " + 
					   "FROM Rating NATURAL JOIN movie " + 
					   "WHERE account_id IN" + 
					   "	(SELECT account_id " + 
					   "	 FROM Account" + 
					   "	 WHERE user_id = ?);";
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
	
		PreparedStatement st = con.prepareStatement(sql);
		st = con.prepareStatement(sql);
	
		st.setString(1, loginuser);
		ResultSet rs = st.executeQuery();

		System.out.print("3B. My ratings: ");
		System.out.println(" MOVIE_TITLE | RATE");
		while(rs.next()) {
			System.out.println("                 " + rs.getString(1)+ " | " + rs.getString(2));
		}
		
		while(rs.next()) {
			System.out.println("                " + rs.getString(1)+ " | " + rs.getString(2));
		}
	}
	
public static boolean Is_admin(String User_id) throws ClassNotFoundException, SQLException {
		
		String sql = "SELECT Is_admin FROM Account WHERE User_id = ?";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);

		PreparedStatement st = con.prepareStatement(sql);
		st = con.prepareStatement(sql);
	
		st.setString(1, User_id);
		ResultSet rs = st.executeQuery();
	
		if (rs.next()) {
			
			if (rs.getBoolean(1)) return true;
			
			else  return false;
		}

		System.out.println("3C. No such id like that");
		return false;
	}
}