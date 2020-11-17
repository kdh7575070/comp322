package entity;

import java.sql.Date;

public class Account {
	private String User_id; 
	private String Password;
	private String First_name;
	private String Last_name;
	private String Phone_number;
	private Date Birthday;
	private String Sex;
	private String Address;
	private String Job; 
	private String Membership_status;
	private boolean Is_admin;
	
	
	public Account(String user_id, String password, String first_name, String last_name, String phone_number,
			Date birthday, String sex, String address, String job, String membership_status, boolean is_admin) {
	
		User_id = user_id;
		Password = password;
		First_name = first_name;
		Last_name = last_name;
		Phone_number = phone_number;
		Birthday = birthday;
		Sex = sex;
		Address = address;
		Job = job;
		Membership_status = membership_status;
		Is_admin = is_admin;
	}
	
	public Account() {
		// TODO Auto-generated constructor stub
	}

	public String getUser_id() {
		return User_id;
	}
	public void setUser_id(String user_id) {
		User_id = user_id;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getFirst_name() {
		return First_name;
	}
	public void setFirst_name(String first_name) {
		First_name = first_name;
	}
	public String getLast_name() {
		return Last_name;
	}
	public void setLast_name(String last_name) {
		Last_name = last_name;
	}
	public String getPhone_number() {
		return Phone_number;
	}
	public void setPhone_number(String phone_number) {
		Phone_number = phone_number;
	}
	public Date getBirthday() {
		return Birthday;
	}
	public void setBirthday(Date birthday) {
		Birthday = birthday;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public String getJob() {
		return Job;
	}
	public void setJob(String job) {
		Job = job;
	}
	public String getMembership_status() {
		return Membership_status;
	}
	public void setMembership_status(String membership_status) {
		Membership_status = membership_status;
	}
	public boolean getIs_admin() {
		return Is_admin;
	}
	public void setIs_admin(boolean is_admin) {
		Is_admin = is_admin;
	}
	
	
}
