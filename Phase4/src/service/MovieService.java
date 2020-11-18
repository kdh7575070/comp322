package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;

public class MovieService {
//	private static String url = "jdbc:postgresql://localhost/testdb";
//	private static String uid = "testdb";
//	private static String pwd = "testdb";
//	private static String driver = "org.postgresql.Driver";
	private static String url = "jdbc:postgresql://localhost/knumovie";
	private static String uid = "postgres";
	private static String pwd = "comp322";
	private static String driver = "org.postgresql.Driver";
			
	
	public static void search_movie(String movie_title) throws ClassNotFoundException, SQLException {
	
	String sql = "SELECT * FROM (MOVIE NATURAL JOIN ACTOR) WHERE Movie_title = ?";

	Class.forName(driver);
	Connection con = DriverManager.getConnection(url, uid, pwd);

	PreparedStatement st = con.prepareStatement(sql);
	st = con.prepareStatement(sql);

	st.setString(1, movie_title);
	ResultSet rs = st.executeQuery();

	rs.next();
	for(int i = 1; i<11 ; i++) System.out.print(rs.getString(i)+" / ");
	System.out.println(rs.getBoolean(11));
	}
	
	public static ArrayList<String> srch_movie(String type, String genre_name, String version_id) throws ClassNotFoundException, SQLException {
	
		String sql = "SELECT * FROM MOVIE WHERE movie_id IN (SELECT MOVIE.movie_id FROM MOVIE_GENRE, MOVIE FULL OUTER JOIN VERSION ON MOVIE.movie_id=VERSION.movie_id WHERE MOVIE.movie_id = MOVIE_GENRE.movie_id";

		if(!(type.equals(""))) sql += " and Type = '" + type + "'";		
		if(!(genre_name.equals(""))) sql += " and Genre_name = '" + genre_name + "'";
		if(!(version_id.equals(""))) sql += " and Version_id = " + version_id;		

		sql+= ")";

		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(sql);

		ArrayList<String> movie_list = new ArrayList<String>();

		while(rs.next()) {
			movie_list.add(rs.getString(1));
			for(int i = 1; i<10 ; i++) System.out.print(rs.getString(i)+" / ");
			System.out.println(rs.getString(10));
		}
		return movie_list;
	}
}