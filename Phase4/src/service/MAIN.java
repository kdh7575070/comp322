package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import entity.Account;
import entity.Movie;

public class MAIN {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 0 회원정보출력
		List<Account> list = AccountService.getList();
		System.out.println(list.get(1).getUser_id());
		
		// 회원객체생성 
		Account a1 = new Account();
		a1.setUser_id("newadmin@newsvine.com");
		a1.setPassword("admin2010");
		a1.setFirst_name("Bill");
		a1.setLast_name("Gates");
		a1.setPhone_number("01022223333");
		a1.setIs_admin(true);
		
		// 1A 회원가입
		System.out.println("1A. User_id = " + a1.getUser_id() +
						   ", Name = " + a1.getFirst_name() + a1.getLast_name() +
						   ", Address = " + a1.getAddress());
//		AccountService.create_account(a1);
		
		// 1D 로그인
		String try_userid = "newadmin@newsvine.com";
		String try_password = "admin2020";
		
		String loginuser = AccountService.login(try_userid, try_password);
		
		// 1B 회원 정보 수정
		String new_first_name = " ";
		String new_last_name = " ";
		String new_phone_number = " ";
		Date new_birthday = java.sql.Date.valueOf("2000-01-01");
		String new_sex = " ";
		String new_address = " ";
		String new_job = " ";
		String new_membership_status = " ";
		Account new_user_info = new Account(loginuser,new_first_name,new_last_name,new_phone_number,new_birthday,new_sex,new_address,new_job,new_membership_status);
		if(!(loginuser.equals(""))) AccountService.update_user_info(new_user_info);
		
		// 1C 비밀 번호 수정
		if(!(loginuser.equals(""))) AccountService.update_user_pwd(loginuser, "admin2020");
		
		// 1E 회원 탈퇴 & 1.F 관리자 계정은 최소 1개 이상 필요 
//		if(!(loginuser.equals(""))) AccountService.delete_account(loginuser);

		// 2B 제목으로 영상물 검색 
		if(!(loginuser.equals(""))) MovieService.search_movie("Future-proofed maximized budgetary management");
		
		// 2C 특정 조으로 영상물 검색 
		if(!(loginuser.equals(""))) MovieService.srch_movie("Movie", "Action", "");
		
		String randomuserid = "ebarron9@shareasale.com";
		
		// 3B 자신의 평가 내역을 확인 
//		if(!(loginuser.equals(""))) AccountService.check_my_ratinglist(loginuser);
		if(!(randomuserid.equals(""))) AccountService.check_my_ratinglist(randomuserid); //결과 확인을 위해
		
		// 3C 관리자는 모든 평가 내역을 확인 가능 
		if(AccountService.Is_admin(loginuser)) AdminService.view_all_ratings();
		else System.out.println("3C. Only admin can approach this view");
		
		// 영화객체생성
		Movie m1 = new Movie();
		m1.setMovie_title("NEW MOVIE 2020");
		m1.setType("Movie");
		m1.setIs_adult(false);
		m1.setRuntime(111);
		
		// 4A 관리자는 새로운 영상물을 등록 가능  
		System.out.println("1A. Movie = " + m1.getMovie_title() +
						   ", Start year = " + m1.getStart_year());
		AdminService.create_movie(m1);
		
		// 4B 관리자는 등록된 영상물을 수정 가능
//		AdminService.update_movie_info(Movie_id, new_info);
		}
}