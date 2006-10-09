package org.syracus.rapid.watches;

import org.syracus.rapid.realm.User;

public class AbstractWatch {

	private Long id;
	private User user;

	/**
	 * 
	 * @return
	 * @hibernate.many-to-one not-null="true"
	*/
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
	
}
