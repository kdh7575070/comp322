package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.Account;
import jep.Jep;
import jep.JepConfig;
import jep.JepException;


public class RecommendationService {
	private static String url = "jdbc:postgresql://localhost/movietest";
	private static String uid = "postgres";
	private static String pwd = "comp322";
	private static String driver = "org.postgresql.Driver";
	
	
	
	public static ArrayList<String> give_recommedation(int sex, int age, int job) throws ClassNotFoundException, SQLException, IOException {
		ArrayList<String> movie_list = new ArrayList<String>();
		String path = System.getProperty("user.dir");
	    System.out.println("Working Directory = " + path);
		try {
			
			
			JepConfig config = new JepConfig();
			config.setIncludePath("./DB_project/phase3/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/comp322/py_package");			
			Jep jep = new Jep(config);
			
//			jep.eval("import numpy as np");
			jep.eval("from joblib import dump, load");
			jep.eval("kmode = load('./DB_project/phase3/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/comp322/kmode.joblib')");
			String sx = Integer.toString(sex);
			String ag = Integer.toString(age);
			String jb = Integer.toString(job);
			String args = "[[" + sx + "," + ag + "," + jb + "]]";
			jep.eval("arg = " + args);
			jep.eval("Test = kmode.predict(arg)");
			jep.eval("data = Test[0]");
			
			
			String clusters = jep.getValue("data").toString();
			jep.close();
			System.out.println(clusters);
			int cluster = Integer.parseInt(clusters);
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
				movie_list.add(rs1.getString(1));
			}
			
			System.out.println("\n\n비슷한 성향의 회원이 가장 재밌게 본 영화");
			while(rs2.next()) {
				System.out.println(rs2.getString(1));
				movie_list.add(rs2.getString(1));
			}
		} catch(JepException e) { 
			
		   System.out.println("Working Directory = " + path);
			e.printStackTrace();
			System.err.println(e.getMessage());
			System.exit(1);
		}
		return movie_list;
	}
}
