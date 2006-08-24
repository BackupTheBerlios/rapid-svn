package org.syracus.rapid.web.filters;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.syracus.rapid.stripes.RapidActionBeanContext;


public class SecurityFilter implements Filter {

protected static final transient Log log = LogFactory.getLog( SecurityFilter.class );
	
	public static final String DEFAULT_LOGIN_URL = "/public/login.jsp";
	
	private String loginUrl = DEFAULT_LOGIN_URL;
	
	public void destroy() {
		if ( log.isDebugEnabled() ) {
			log.debug( "[destroy] destroying SecurityFilter." );
		}
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
		throws IOException, ServletException
	{
		if ( !(arg0 instanceof HttpServletRequest) ) {
			throw new ServletException( "SecurityFilter only supports HTTP requests." );
		}
		HttpServletRequest request = (HttpServletRequest)arg0;
		if ( null != request.getSession().getAttribute( RapidActionBeanContext.AUTH_USER ) ) {
			arg2.doFilter( arg0, arg1 );
		} else {
			String contextPath = request.getContextPath();
			String requestUri = request.getRequestURI();
			String queryString = request.getQueryString();
			String redirectUrl = null;
			if ( requestUri.startsWith( contextPath ) ) {
				redirectUrl = requestUri.substring( contextPath.length() );
			} else {
				redirectUrl = requestUri;
			}
			if ( null != queryString && 0 < queryString.trim().length() ) {
				redirectUrl = redirectUrl + "?" + queryString;
			}
			if ( log.isDebugEnabled() ) {
				log.debug( "[doFilter] contextPath = '" + contextPath + "'" );
				log.debug( "[doFilter] requestUri = '" + requestUri + "'" );
				log.debug( "[doFilter] queryString = '" + queryString + "'" );
				log.debug( "[doFilter] redirectUrl = '" + redirectUrl + "'" );
				log.debug( "[doFilter] loginUrl = '" + this.loginUrl + "'" );
			}
			
			HttpServletResponse response = (HttpServletResponse)arg1;
			response.sendRedirect( contextPath + this.loginUrl + "?target=" + URLEncoder.encode( redirectUrl, "UTF-8" ) );
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		if ( log.isDebugEnabled() ) {
			log.debug( "[init] BEGIN" );
		}
		if ( null != arg0.getInitParameter( "login-url" ) ) {
			this.loginUrl = arg0.getInitParameter( "login-url" );
		} else if ( log.isWarnEnabled() ) {
			log.warn( "No login-url given. Using default '" + this.loginUrl + "'" );
		}
		if ( log.isDebugEnabled() ) {
			log.debug( "[init] END" );
		}

	}

}
