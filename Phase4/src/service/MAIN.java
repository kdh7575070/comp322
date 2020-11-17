package service;

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
		
		String loginuser = AccountService.login(try_user, "asfdgk");

		if(!(loginuser.equals("")))AccountService.search_movie("Future-proofed maximized budgetary management");
		if(!(loginuser.equals("")))AccountService.srch_movie("Movie", "Action", "");
		
		if(loginuser.equals(try_user)) AccountService.delete_account(try_user);
		}
}
