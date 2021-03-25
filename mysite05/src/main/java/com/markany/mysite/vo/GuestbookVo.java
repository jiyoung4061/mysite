package com.markany.mysite.vo;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name="mysite_guestbook")
public class GuestbookVo {
	
	@Column(name="no")
	private long no;
	
	@Column(name="name")
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Column(name="message")
	private String message;
	
	@Column(name="time")
	private String regDate;
	
	public long getNo() {
		return no;
	}
	public void setNo(long no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getReg_date() {
		return regDate;
	}
	public void setReg_date(String regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "GuestbookVo [no=" + no + ", name=" + name + ", password=" + password + ", message=" + message
				+ ", reg_date=" + regDate + "]";
	}
}
