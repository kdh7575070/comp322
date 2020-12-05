package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.Account;


public class RecommendationService {
	private static String url = "jdbc:postgresql://localhost/testdb";
	private static String uid = "taeha";
	private static String pwd = "testdb";
	private static String driver = "org.postgresql.Driver";
	
	public static void give_recommedation(Account account) throws ClassNotFoundException, SQLException, IOException {
		//파이썬으로 [나이 성별 직업] 보내서 cluster 알아내고
		String pythonPath = "./predict_cluster.py";
		String[] cmd = new String[3];
		cmd[0] = "python";
		cmd[1] = pythonPath;
		cmd[2] = "1 3 3";
		
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec(cmd);
		
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		
		String ret = in.readLine();
		System.out.println("value is : "+ret);
		
		int cluster =2;
		// cluster넣으면 추천 영화 나오도록.
		String sql1 = "select movie_title from movie where movie_id in "
				+ "(select movie_id from cluster_movie_count_avg "
				+ "where cluster=? and count > 3 order by avg desc limit 2);";
		
		String sql2 = "select movie_title from movie where movie_id in "
				+ "(select movie_id from cluster_movie_count_avg where cluster=? "
				+ "order by count desc limit 2)";
		
		Class.forName(driver);
		Connection con = DriverManager.getConnection(url, uid, pwd);
		
		PreparedStatement st1 = con.prepareStatement(sql1);
		PreparedStatement st2 = con.prepareStatement(sql2);
		
		st1.setInt(1, cluster);
		st2.setInt(1, cluster);
		
		ResultSet rs1 = st1.executeQuery();
		ResultSet rs2 = st2.executeQuery();
		
		
		System.out.println("Recommedation ");
		System.out.println("비슷한 성향의 회원이 가장 많이 본 영화");
		while(rs1.next()) {
			System.out.println(rs1.getString(1));
		}
		
		System.out.println("\n\n비슷한 성향의 회원이 가장 재밌게 본 영화");
		while(rs2.next()) {
			System.out.println(rs2.getString(1));
		}						
	}
}
