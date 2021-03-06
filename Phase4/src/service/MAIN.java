package service;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.Account;
import entity.Movie;
import jep.JepException;

public class MAIN {

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException, JepException {
		// 0 회원정보출력
//		List<Account> list = AccountService.getList();
//		System.out.println(list.get(1).getUser_id());
//		System.out.println();
		
//		System.out.println("1-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		System.out.println();
//		
//		// 회원객체생성
//		Account a1 = new Account();
//		a1.setUser_id("newadmin@newsvine.com");
//		a1.setPassword("admin2010");
//		a1.setFirst_name("Bill");
//		a1.setLast_name("Gates");
//		a1.setPhone_number("01022223333");
//		a1.setSex("F");
//		a1.setIs_admin(true);
//		
//		// 1A 회원가입
//		System.out.print("1A. User_id = " + a1.getUser_id() +
//						   ", Name = " + a1.getFirst_name() + a1.getLast_name() +
//						   ", Address = " + a1.getAddress() +
//						   " / ");
//		AccountService.create_account(a1);
//		System.out.println();
//		
//		// 1D 로그인
//		String try_userid = "newadmin@newsvine.com";
//		String try_password = "admin2010";
//		
//		String loginuser = AccountService.login(try_userid, try_password);
//		System.out.println();
//		
//		// 수정될 객체 생성
//		String new_first_name = "Jongbin";
//		String new_last_name = "Woo";
//		String new_phone_number = "01012345678";
//		Date new_birthday = java.sql.Date.valueOf("2000-01-01");
//		String new_sex = "F";
//		String new_address = "Daegu Bukgo Gyeongdaero";
//		int new_job = 2;
//		Account new_user_info = new Account(loginuser,new_first_name,new_last_name,new_phone_number,new_birthday,new_sex,new_address,new_job);
//		
//		// 1B 회원 정보 수정
//		if(!(loginuser.equals(""))) AccountService.update_user_info(new_user_info);
//		System.out.println();
//		
//		// 1C 비밀 번호 수정
//		if(!(loginuser.equals(""))) AccountService.update_user_pwd(loginuser, "admin2020");
//		System.out.println();
//		
//		// 1E 회원 탈퇴 & 1F 관리자 계정은 최소 1개 이상 필요 
////		if(!(loginuser.equals(""))) AccountService.delete_account(loginuser); // 맨 마지막에서 확인
////		System.out.println();
//
//		System.out.println("2-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		System.out.println();
//		
//		// 2A 전체 영상물 보기
//		if(!(loginuser.equals(""))) MovieService.show_all_movies();
//		System.out.println();
//		
//		// 2B 제목으로 영상물 검색 
//		if(!(loginuser.equals(""))) MovieService.search_movie(loginuser, "Heat");
//		System.out.println();
//		
//		// 2C 특정 조건으로 영상물 검색 & 2.E 회원이 평가한 영상물은 이후 검색대상에서 제외 
//		ArrayList<String> movie_list = new ArrayList<String>();
//		if(!(loginuser.equals(""))) movie_list = MovieService.srch_movie(loginuser, "Movie", "Action", "");
//		System.out.println();
//		
//		// 2D 영화 상제정보 & 3D 평균평점 확인  
//		MovieService.movie_info("Heat");
//		System.out.println();
//
//		// 2D 해당 영상물을 평가
//		if(!(loginuser.equals(""))) {
//			MovieService.movie_rate(loginuser, movie_list.get(0), false, 8);
//			System.out.println(" : " + movie_list.get(0));
//		}
//		System.out.println();
//		
//		System.out.println("3-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		System.out.println();
//		
//		
//
//		// 3A true
//		
//		// 3B 자신의 평가 내역을 확인 
//		if(!(loginuser.equals(""))) AccountService.check_my_ratinglist(loginuser);
////		if(!(randomuserid.equals(""))) AccountService.check_my_ratinglist(randomuserid); //결과 확인을 위해
//		System.out.println();
//		
//		// 3C 관리자는 모든 평가 내역을 확인 가능 
////		if(AccountService.Is_admin(loginuser)) AdminService.view_all_ratings();
////		else System.out.println("3C. Only admin can approach this view");
////		System.out.println();
//		
//		System.out.println("4-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
//		System.out.println();
//		
//		// 영화객체생성
//		Movie m1 = new Movie();
//		m1.setMovie_title("NEW MOVIE 2020");
//		m1.setType("Movie");
//		m1.setIs_adult(false);
//		m1.setRuntime(111);
//		
//		// 4A 관리자는 새로운 영상물을 등록 가능  
//		System.out.println("4A. Movie = " + m1.getMovie_title() +
//						   ", Start year = " + m1.getStart_year());
//		AdminService.create_movie(m1);
//		System.out.println();
//		
//		// 수정될 객체 생성
//		Movie new_movie_info = new Movie();
//		new_movie_info.setMovie_title("NEW MOVIE 2021");
//		new_movie_info.setType("Movie");
//		new_movie_info.setIs_adult(true);
//		new_movie_info.setStart_year(java.sql.Date.valueOf("2000-01-01"));
//		
//		// 4B 관리자는 등록된 영상물을 수정 가능
//		AdminService.update_movie_info(301, new_movie_info);
//		System.out.println();
//		
//		// 1E 회원탈퇴 
//		if(!(loginuser.equals(""))) AccountService.delete_account(loginuser);
		RecommendationService.give_recommedation(1, 20, 3);

	}
}