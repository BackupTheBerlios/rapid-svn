package org.syracus.rapid.actions.realm;

import javax.servlet.http.HttpSession;

import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.syracus.rapid.profiles.UserProfile;
import org.syracus.rapid.realm.User;

@UrlBinding("/public/login.action")
public class LoginActionBean extends RealmActionBean {

	public static final String DEFAULT_TARGET_URL = "/protected/workbench.jsp";
	public static final String DEFAULT_LOGIN_URL = "/public/login.jsp";
	
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
			// load user roles
			user.setRoles( user.getRoles() );
			getContext().setAuthUser( user );
			// try to load users profile
			UserProfile profile = getProfileService().getUserProfile( user.getId() );
			if ( null == profile ) {
				profile = UserProfile.getDefaultProfile( user.getId() );
				getProfileService().addUserProfile( profile );
			}
			getContext().setUserProfile( profile );
			resolution = new RedirectResolution( getTarget() );
		}
		return( resolution );
	}
	
	public Resolution logout() {
		HttpSession session = getContext().getRequest().getSession();
		if ( null != session ) {
			session.invalidate();
		}
		return( new RedirectResolution( DEFAULT_LOGIN_URL ) );
	}
}
