package org.syracus.rapid.stripes;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.ActionBeanContext;

import org.syracus.rapid.realm.User;

public class RapidActionBeanContext extends ActionBeanContext {

	public static final String AUTH_USER = "org.syracus.rapid.realm.AUTH_USER";
	
	/**
	 * Returns the currently active user for this session.
	 * 
	 * @return The authenticated user or null of no user has been authenticated.
	 */
	public User getAuthUser() {
		HttpSession session = getRequest().getSession();
		return( (User)session.getAttribute( AUTH_USER ) );
	}
	
	/**
	 * Applies a new user account to the current session.
	 * If no session exists it will be created dynamically.
	 * 
	 * @param user
	 */
	public void setAuthUser( User user ) {
		HttpSession session = getRequest().getSession();
		session.setAttribute( AUTH_USER, user );
	}
}
