package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import entity.Account;

public class MAIN {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<Account> list = AccountService.getList();
		System.out.println(list.get(1).getUser_id());
		
		
		// 1.A 회원가입
		Account a1 = new Account();
		a1.setUser_id("jaa1@newsvine.com");
		a1.setPassword("asfdgk");
		a1.setFirst_name("Bill");
		a1.setLast_name("Gates");
		a1.setPhone_number("01022223333");
		System.out.println(a1.getAddress());
		AccountService.create_account(a1);
		
		// 1.D 로그인
		String try_user = "jaa1@newsvine.com";
		String try_password = "asfdgk";
		
		String loginuser = AccountService.login(try_user, try_password);
		
		// 1.B 회원 정보 수정
		String new_first_name = " ";
		String new_last_name = " ";
		String new_phone_number = " ";
		Date new_birthday = java.sql.Date.valueOf("2000-01-01");
		String new_sex = " ";
		String new_address = " ";
		String new_job = " ";
		String new_membership_status = " ";
		
		Account new_user_info = new Account(loginuser,new_first_name,new_last_name,new_phone_number,new_birthday,new_sex,new_address,new_job,new_membership_status);
		AccountService.update_user_info(new_user_info);
		
		// 1.C 비밀 번호 수정
		AccountService.update_user_pwd(loginuser, "ddddd");
		
		// 1.E 회원 탈퇴 & 1.F 관리자 계정은 최소 1개 이상 필요 
		if(loginuser.equals(try_user)) AccountService.delete_account(try_user);

		// 2.B 제목으로 영상물 검색 
		if(!(loginuser.equals("")))AccountService.search_movie("Future-proofed maximized budgetary management");
		
		// 2.C 특정 조선으로 영상물 검색 
		if(!(loginuser.equals("")))AccountService.srch_movie("Movie", "Action", "");
		
		
		}
}
