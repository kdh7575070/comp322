package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

import entity.Account;
import entity.Rating;

public class MovieService {
//	private static String url = "jdbc:postgresql://localhost/testdb";
//	private static String uid = "testdb";
//	private static String pwd = "testdb";
//	private static String driver = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost/knumovie";
	private static String uid = "postgres";
	private static String pwd = "comp322";
	private static String driver = "org.postgresql.Driver";
	
	public static void show_all_movies() throws ClassNotFoundException, SQLException {
		
		String sql = "SELECT * FROM Movie";
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);	
		
		System.out.println("2A. ALL MOVIEs ");
		
		while(rs.next()) {
			System.out.println(rs.getInt(1) +"    |    "+rs.getString(2));
		}
	}
	
	public static void search_movie(String loginuser, String movie_title) throws ClassNotFoundException, SQLException {
	
		String sql = ""
				+ "SELECT Movie_id, Movie_title From movie where movie_id in (SELECT Movie_id FROM Movie WHERE Movie_title = ?"
				+ "EXCEPT SELECT R.Movie_id FROM Rating as R WHERE (R.Account_id  IN " + 
				"		(SELECT A.Account_id FROM Account AS A WHERE A.User_id = ?)))";
	
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
	
		PreparedStatement st = con.prepareStatement(sql);
		st = con.prepareStatement(sql);
		
	
		st.setString(1, movie_title);
		st.setString(2, loginuser);
		
		ResultSet rs = st.executeQuery();	
		
		System.out.println("2B. Result is ");
		
		while(rs.next()) {
			System.out.println(rs.getInt(1) +"    |    "+rs.getString(2));
		}
	}
	
	public static ArrayList<String> srch_movie(String loginuser, String type, String genre_name, String version_id) throws ClassNotFoundException, SQLException {
	
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
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		ArrayList<String> movie_list = new ArrayList<String>();
		
		System.out.println("2C. Result is ");

		
		while(rs.next()) {
			
			System.out.println(rs.getInt(1) +"    |    "+rs.getString(2));
			
	
			movie_list.add(rs.getString(2));
		}
		return movie_list;
	}
	
	public static void movie_rate(String loginuser, String movie_title, boolean likes, int ratings) throws ClassNotFoundException, SQLException {
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
		
		new_rating.setLikes(likes);
		new_rating.setRatings(ratings);
		
		String sql = "INSERT INTO Rating ( "
				+ "		Account_id,"
				+ "		Movie_id,"
				+ "		Likes,"
				+ "		Ratings"
				+ ") VALUES (?,?,?,?)";
		
		
		PreparedStatement st1 = con.prepareStatement(sql);
		st1.setInt(1, new_rating.getAccount_id());
		st1.setInt(2, new_rating.getMovie_id());
		st1.setBoolean(3, new_rating.getLikes());
		st1.setInt(4, new_rating.getRatings());
		
		int result = st1.executeUpdate();
		if (result == 1)
			System.out.println("Rating created successfully");
	}
}