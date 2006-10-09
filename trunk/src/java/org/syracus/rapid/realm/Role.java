package org.syracus.rapid.realm;

import java.util.Set;

/**
 * 
 * @author snwiem
 * @hibernate.class table="roles"
 */
public class Role {

	private Long id;
	private String name;
	
	private Set<User> users;

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
	 * @hibernate.property not-null="true"
	 */
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return
	 * @hibernate.set lazy="true" table="user_roles"
	 * @hibernate.key column="role_id"
	 * @hibernate.many-to-many column="user_id" class="org.syracus.rapid.realm.User"
	 */
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
	
	
}
