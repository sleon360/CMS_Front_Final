package com.appcms.entity;

public class CredencialesEntity {

	private String userName;
	private String password;
	private String TOKENONE;
	private String TOKENTWO;
	private Scotiauser scotiauser;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTOKENONE() {
		return TOKENONE;
	}

	public void setTOKENONE(String tOKENONE) {
		TOKENONE = tOKENONE;
	}

	public String getTOKENTWO() {
		return TOKENTWO;
	}

	public void setTOKENTWO(String tOKENTWO) {
		TOKENTWO = tOKENTWO;
	}

	public Scotiauser getScotiauser() {
		return scotiauser;
	}

	public void setScotiauser(Scotiauser scotiauser) {
		this.scotiauser = scotiauser;
	}

	@Override
	public String toString() {
		return "CredencialesEntity [userName=" + userName + ", password=" + password + ", TOKENONE=" + TOKENONE
				+ ", TOKENTWO=" + TOKENTWO + ", scotiauser=" + scotiauser + "]";
	}

}
