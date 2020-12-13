package service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import entity.Rating;

public class MovieService {
	private static String url = "jdbc:postgresql://localhost/movietest";
	private static String uid = "postgres";
	private static String pwd = "comp322";
	private static String driver = "org.postgresql.Driver";
			
	
	public static ArrayList<String> show_all_movies() throws ClassNotFoundException, SQLException {
		
		String sql = "SELECT movie_title FROM Movie";
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);	
		ArrayList<String> movie_list = new ArrayList<String>();
		System.out.println("2A. ALL MOVIEs are");
		
		while(rs.next()) {
			movie_list.add(rs.getString(1));
			
		}
		return movie_list;
	}
	
	public static ArrayList<String> search_movie(String loginuser, String movie_title) throws ClassNotFoundException, SQLException, InterruptedException {
	
		String sql = ""
				+ "SELECT Movie_id, Movie_title From movie where movie_id in (SELECT Movie_id FROM Movie WHERE Movie_title = ?"
				+ "EXCEPT SELECT R.Movie_id FROM Rating as R WHERE (R.Account_id  IN " + 
				"		(SELECT A.Account_id FROM Account AS A WHERE A.User_id = ?)))";
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
//		DatabaseMetaData dbMetaData = con.getMetaData();
//		if (dbMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
//			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//			System.out.println("Connection.TRANSACTION_SERIALIZABLE");
//		}
		con.setAutoCommit(false);
		String sql_t = "";
		sql_t = "set transaction isolation level serializable";
		PreparedStatement pstmt = con.prepareStatement(sql_t);
		int rs_t1= pstmt.executeUpdate();
		sql_t = "BEGIN";
		pstmt = con.prepareStatement(sql_t);
		rs_t1=pstmt.executeUpdate();
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, movie_title);
		st.setString(2, loginuser);
//		System.out.println(con.getTransactionIsolation());
		Thread.sleep(1000);
		ResultSet rs = st.executeQuery();	
		
		ArrayList<String> movie_list = new ArrayList<String>();
		
		System.out.println("2B. Result is ");
		while(rs.next()) {
			System.out.printf("%3d", rs.getInt(1));
			System.out.println(" " + rs.getString(2));
			movie_list.add(rs.getString(2));
		}
		
		sql_t = "COMMIT";
		pstmt = con.prepareStatement(sql_t);
		rs_t1=pstmt.executeUpdate();
		con.commit();
		con.setAutoCommit(true);
		return movie_list;
	}
	
	public static ArrayList<String> srch_movie(String loginuser, String type, String genre_name, String version_id) throws ClassNotFoundException, SQLException, InterruptedException {
	
		String sql = "SELECT Movie_id, Movie_title FROM MOVIE WHERE movie_id IN "
				+ "((SELECT MOVIE.movie_id FROM MOVIE_GENRE, MOVIE FULL OUTER JOIN VERSION ON MOVIE.movie_id=VERSION.movie_id WHERE MOVIE.movie_id = MOVIE_GENRE.movie_id";
		if(!(type.equals(""))) sql += " and Type = '" + type + "'";		
		if(!(genre_name.equals(""))) sql += " and Genre_name = '" + genre_name + "'";
		if(!(version_id.equals(""))) sql += " and Version_id = " + version_id;		
		sql+= ")";
		
		sql += "  EXCEPT  "
				+ "SELECT R.Movie_id FROM Rating as R WHERE R.Account_id IN (SELECT A.Account_id FROM Account AS A WHERE A.User_id = '"
				+ loginuser
				+ "'))";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		DatabaseMetaData dbMetaData = con.getMetaData();
//		if (dbMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
//			con.setTransactionIsolation(8);
//		}
		Statement st = con.createStatement();
		TimeUnit.SECONDS.sleep(5);
		ResultSet rs = st.executeQuery(sql);

		ArrayList<String> movie_list = new ArrayList<String>();
		
		System.out.println("2C. Result is ");

		
		while(rs.next()) {
			

			System.out.printf("%3d", rs.getInt(1));
			System.out.println(" " + rs.getString(2));
			
	
			movie_list.add(rs.getString(2));
		}
		return movie_list;
	}
	
	public static int movie_rate(String loginuser, String movie_title,int ratings) throws ClassNotFoundException, SQLException {
		int movie_id=0, account_id = 0;
		String sql1 = "SELECT account_id FROM ACCOUNT WHERE user_id = '" + loginuser + "'";
		String sql2 = "SELECT movie_id FROM Movie WHERE movie_title = '" + movie_title + "'";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		Statement st = con.createStatement();
		
		ResultSet rs = st.executeQuery(sql1);
		while(rs.next())
			account_id = rs.getInt(1);
		
		rs = st.executeQuery(sql2);
		while (rs.next())
			movie_id = rs.getInt(1);
		
		Rating new_rating = new Rating(account_id, movie_id);
		new_rating.setRatings(ratings);
		
		String sql = "INSERT INTO Rating ( "
				+ "		Account_id,"
				+ "		Movie_id,"
				+ "		Ratings"
				+ ") VALUES (?,?,?)";
		
		
		PreparedStatement st1 = con.prepareStatement(sql);
		st1.setInt(1, new_rating.getAccount_id());
		st1.setInt(2, new_rating.getMovie_id());
		st1.setInt(3, new_rating.getRatings());
		
		int result = st1.executeUpdate();
		if (result == 1)
			System.out.print("2D. Successfully created rating");
		return result;
	}
	
	public static void movie_info(String movie_title) throws ClassNotFoundException, SQLException {

		String sql = "select * from movie natural join movie_genre where movie_title = ?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
	
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, movie_title);

		System.out.println("2D. This movie info ");
		
		ResultSet rs = st.executeQuery();
		rs.next();
		
		System.out.println("    제목 : " + rs.getString(2));
		System.out.println("    타입 : " + rs.getString(3));
		if(rs.getString(3).equals("Series")) System.out.println("    소속 시리즈 : " + rs.getString(4));
		if(rs.getString(3).equals("Series")) System.out.println("    시리즈 번호 : " + rs.getString(5));
		if(rs.getBoolean(6)) System.out.println ("    성인등급영화");
		else System.out.println ("    전체관람가");
		System.out.println("    감독 : " + rs.getString(7) + " " + rs.getString(8));
		System.out.println("    상영시작일 : " + rs.getString(9));
		System.out.println("    상영시간 : " + rs.getString(10) + "분");
		System.out.println("    장르 : " + rs.getString(11));

		sql = "select avg(ratings) from movie natural join rating where movie_title = ?";
		
		st = con.prepareStatement(sql);
		st.setString(1, movie_title);
		
		rs = st.executeQuery();
		rs.next();
		System.out.println("    평균평점 : " + rs.getString(1));
		
		sql = "select concat(cast_first_name, ' ', cast_last_name), concat(real_first_name, ' ', real_last_name) from (movie natural join starred_by) natural join actor where movie_title = ? ";
		
		st = con.prepareStatement(sql);
		st.setString(1, movie_title);
		
		rs = st.executeQuery();
		System.out.print("    출연진정보 : ");
		
		while(rs.next()) {
		
		System.out.print(rs.getString(1));
		System.out.print("(" + rs.getString(2) + ") | ");
		}
		
		System.out.println();
	}
}