package service;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.sql.Date;
import entity.Movie;

public class AdminService {
	private static String url = "jdbc:postgresql://localhost/movietest";
	private static String uid = "postgres";
	private static String pwd = "comp322";
	private static String driver = "org.postgresql.Driver";

	public static int create_movie(Movie movie) throws ClassNotFoundException, SQLException {
		/*
		 * 4.A  새로운 영상물 등록
		 * 새로운 Movie정보를 가지고 있는 movie객체를 받아와서 insert
		 */
		String Movie_title = movie.getMovie_title();
		String Type = movie.getType();
		int Parent_id = movie.getParent_id();
		int Series_number = movie.getSeries_number();
		boolean Is_adult = movie.getIs_adult();
		String Director_first_name = movie.getDirector_first_name();
		String Director_last_name = movie.getDirector_last_name();
		Date Start_year = movie.getStart_year();
		int Runtime = movie.getRuntime();

		try {
		String sql = "INSERT INTO Movie ( "
				+ "		Movie_title,"
				+ "		Type,"
				+ "		Parent_id,"
				+ "		Series_number,"
				+ "		Is_adult,"
				+ "		Director_first_name,"
				+ "		Director_last_name,"
				+ "		Start_year,"
				+ "		Runtime"
				+ ") VALUES (?,?,?,?,?,?,?,?,?)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
//		DatabaseMetaData dbMetaData = con.getMetaData();
//		if (dbMetaData.supportsTransactionIsolationLevel(Connection.TRANSACTION_SERIALIZABLE)) {
//			con.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
//			System.out.println("Connection.TRANSACTION_SERIALIZABLE");
//		}
		con.setAutoCommit(false);
		String sql_t1 = "";
		sql_t1 = "set transaction isolation level serializable";
		PreparedStatement pstmt_t1 = con.prepareStatement(sql_t1);
		int rs_t1=pstmt_t1.executeUpdate();
		
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, Movie_title);
		st.setString(2, Type);
		st.setInt(3, Parent_id);
		st.setInt(4, Series_number);
		st.setBoolean(5, Is_adult);
		st.setString(6, Director_first_name);
		st.setString(7, Director_last_name);
		st.setDate(8, Start_year);
		st.setInt(9, Runtime);
		
		int rs = st.executeUpdate();
		System.out.println("Movie created successfully");
		Thread.sleep(1000);
		sql_t1 = "commit";
		pstmt_t1 = con.prepareStatement(sql_t1);
		rs_t1=pstmt_t1.executeUpdate();
		con.commit();
		con.setAutoCommit(true);
		return 1;
		} catch (Exception e) {
			
			e.printStackTrace();
		
		return 0;
		}
	}
	
	public static int update_movie_info(Movie new_info) throws ClassNotFoundException, SQLException {
		/*
		 * 4.B 등록된 영상물의 정보를 수정
		 * Movie_id와 새로운 Movie정보를 가지고 있는 new_info 객체를 받아와서
		 * Movie_id에 해당하는 Movie정보 new_info의 정보를 넣어준다.
		 */
		String sql = "UPDATE Movie "
				+ "SET"
				+ "		Type=?,"
				+ "		Parent_id=?,"
				+ "		Series_number=?,"
				+ "		Is_adult=?,"
				+ "		Director_first_name=?,"
				+ "		Director_last_name=?,"
				+ "		Start_year=?,"
				+ "		Runtime=?"
				+ "   WHERE Movie_title=?";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url,uid,pwd);
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, new_info.getType());
		st.setInt(2, new_info.getParent_id());
		st.setInt(3, new_info.getSeries_number());
		st.setBoolean(4, new_info.getIs_adult());
		st.setString(5, new_info.getDirector_first_name());
		st.setString(6, new_info.getDirector_last_name());
		st.setDate(7, new_info.getStart_year());
		st.setInt(8, new_info.getRuntime());
		st.setString(9, new_info.getMovie_title());
		
		int rs = st.executeUpdate();
		if (rs == 1) System.out.println("4B. Update Successfully");
		st.close();
		con.close();
		
		return rs;
	}
	
	public static void view_all_ratings() throws ClassNotFoundException, SQLException {
		/*
		 * 3.C 모든 평가 내역 확인
		 */
		String sql = "SELECT movie_title, user_id, ratings "
				+ "FROM Rating NATURAL JOIN movie NATURAL JOIN Account "
				+ "ORDER BY Movie_title";
				   
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		if(rs.next()) {
			System.out.print("3C. All ratings: ");
			System.out.println(rs.getString(1)+ " | " + rs.getString(2)+ " | " +  rs.getString(3));
		}
		
		while(rs.next()) {
			System.out.println("                 " + rs.getString(1)+ " | " + rs.getString(2)+ " | " +  rs.getString(3));
		}
	}
}