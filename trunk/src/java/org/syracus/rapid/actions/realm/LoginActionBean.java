package org.syracus.rapid.actions.realm;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

import org.syracus.rapid.realm.User;

public class LoginActionBean extends RealmActionBean {

	public static final String DEFAULT_TARGET_URL = "/protected/index.jsp";
	
	private String account;
	private String password;
	private String targetUrl = DEFAULT_TARGET_URL;
	
	public String getTargetUrl() {
		return targetUrl;
	}

	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Resolution login() {
		User user = getRealmService().authenticateUser( getAccount(), getPassword() );
		Resolution resolution = null;
		if ( null != user ) {
			getContext().setAuthUser( user );
			resolution = new RedirectResolution( getTargetUrl() );
		}
		return( resolution );
	}
	
	public Resolution logout() {
		HttpSession session = getContext().getRequest().getSession();
		if ( null != session ) {
			session.invalidate();
		}
		return( new RedirectResolution( "/public/login.jsp" ) );
	}
}
