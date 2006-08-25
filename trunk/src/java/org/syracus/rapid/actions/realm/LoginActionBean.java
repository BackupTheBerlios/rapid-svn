package org.syracus.rapid.actions.realm;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.syracus.rapid.realm.User;

@UrlBinding("/public/login.action")
public class LoginActionBean extends RealmActionBean {

	public static final String DEFAULT_TARGET_URL = "/protected/index.jsp";
	
	private String account;
	private String password;
	private String target = DEFAULT_TARGET_URL;
	
	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
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
		Resolution resolution = getContext().getSourcePageResolution();
		if ( null != user ) {
			getContext().setAuthUser( user );
			resolution = new RedirectResolution( getTarget() );
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
