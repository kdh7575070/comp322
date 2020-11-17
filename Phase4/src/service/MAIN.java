package service;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import entity.Account;

public class MAIN {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		List<Account> list = AccountService.getList();
		System.out.println(list.get(1).getUser_id());
		
		Account a1 = new Account();
		a1.setUser_id("jaa1@newsvine.com");
		a1.setPassword("asfdgk");
		a1.setFirst_name("Bill");
		a1.setLast_name("Gates");
		a1.setPhone_number("01022223333");
		System.out.println(a1.getAddress());
		AccountService.create_account(a1);
		
		String try_user = "jaa1@newsvine.com";
		String try_password = "asfdgk";
		
		String loginuser = AccountService.login(try_user, try_password);
		
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
		
		if(!(loginuser.equals("")))AccountService.search_movie("Future-proofed maximized budgetary management");
		if(!(loginuser.equals("")))AccountService.srch_movie("Movie", "Action", "");
		
		if(loginuser.equals(try_user)) AccountService.delete_account(try_user);
		}
}
