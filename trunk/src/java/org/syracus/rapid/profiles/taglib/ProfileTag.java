package org.syracus.rapid.profiles.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.syracus.rapid.profiles.UserProfile;
import org.syracus.rapid.stripes.RapidActionBeanContext;

public class ProfileTag extends TagSupport {

	protected static final transient Log log = LogFactory.getLog( ProfileTag.class );
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2784668970171456505L;
	private String key;
	private String var;
	private String dflt;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getVar() {
		return var;
	}
	public void setVar(String var) {
		this.var = var;
	}
	
	
	public String getDflt() {
		return dflt;
	}
	public void setDflt(String dflt) {
		this.dflt = dflt;
	}
	@Override
	public int doStartTag() throws JspException {
		UserProfile profile = (UserProfile)pageContext.getSession().getAttribute( RapidActionBeanContext.AUTH_USER_PROFILE );
		if ( null != profile ) {
			if ( log.isDebugEnabled() ) {
				log.debug( "[doStartTag] user profile found, trying to get property value '" + getKey() + "'" );
			}
			String value = profile.getProperty( getKey() );
			if ( null != value ) {
				if ( log.isDebugEnabled() ) {
					log.debug( "[doStartTag] value for property '" + getKey() + "' is '" + value + "'" );
				}
				pageContext.setAttribute( getVar(), value );
				return( EVAL_PAGE );
			} else if ( null != getDflt() ) {
				if ( log.isDebugEnabled() ) {
					log.debug( "[doStartTag] default value specified using '" + getDflt() + "'" );
				}
				pageContext.setAttribute( getVar(), getDflt() );
				return( EVAL_PAGE );
			} else {
				if ( log.isDebugEnabled() ) {
					log.debug( "[doStartTag] no value found for key '" + getKey() + "'...looking for default value." );
				}
				// check if we have a default value for this property
				if ( null != ( value = UserProfile.getDefaultValue( getKey() ) ) ) {
					if ( log.isDebugEnabled() ) {
						log.debug( "[doStartTag] default value for property '" + getKey() + "' is '" + value + "'" );
					}
					pageContext.setAttribute( getVar(), value );
					return( EVAL_PAGE );
				} else {
					if ( log.isDebugEnabled() ) {
						log.debug( "[doStartTag] no default value found for property '" + getKey() + "', skipping body" );
					}
					return( SKIP_BODY );
				}
			}
		} else if ( null != getDflt() ) {
			if ( log.isDebugEnabled() ) {
				log.debug( "[doStartTag] no user profile available but default value specified using '" + getDflt() + "'" );
			}
			pageContext.setAttribute( getVar(), getDflt() );
			return( EVAL_PAGE );
		} else {
			if ( log.isDebugEnabled() ) {
				log.debug( "[doStartTag] no user profile available, looking for default value of property '" + getKey() + "'" );
			}
			String value = UserProfile.getDefaultValue( getKey() );
			if ( null != value ) {
				if ( log.isDebugEnabled() ) {
					log.debug( "[doStartTag] default value for property '" + getKey() + "' is '" + value + "'" );
				}
				pageContext.setAttribute( getVar(), value );
				return( EVAL_PAGE );
			} else {
				if ( log.isDebugEnabled() ) {
					log.debug( "[doStartTag] no default value found for property '" + getKey() + "', skipping body" );
				}
				return( SKIP_BODY );
			}
		}
	}
	
	
}
