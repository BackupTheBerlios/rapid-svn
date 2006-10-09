package org.syracus.rapid.realm;

import java.util.Set;

/**
 * 
 * @return
 * @hibernate.class table="users"
 */
public class User extends Account {

	private String name;
	private String email;
	
	private Set<Role> roles;
	
	/**
	 * 
	 * @return
	 * @hibernate.set lazy="true" table="user_roles" inverse="true"
	 * @hibernate.key column="user_id"
	 * @hibernate.many-to-many  column="role_id" class="org.syracus.rapid.realm.Role"
	 */
	public Set<Role> getRoles() {
		return roles;
	}
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
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
