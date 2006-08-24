package org.syracus.rapid.realm;

/**
 * 
 * @return
 * @hibernate.class table="users"
 */
public class User extends Account {

	private String name;
	private String email;
	
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * 
	 * @return
	 * @hibernate.property
	 */
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
