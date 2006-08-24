package org.syracus.rapid.realm;

public class Account {

	private Long id;
	private String account;
	private String password;
	
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 
	 * @return
	 * @hibernate.id generator-class="native"
	 */
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
