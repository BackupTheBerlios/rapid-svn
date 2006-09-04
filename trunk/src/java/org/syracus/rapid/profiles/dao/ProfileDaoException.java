package org.syracus.rapid.profiles.dao;

import org.springframework.core.NestedRuntimeException;

public class ProfileDaoException extends NestedRuntimeException {

	private static final long serialVersionUID = 4033950236881232976L;

	public ProfileDaoException( String message ) {
		super( message );
	}
	
	public ProfileDaoException( String message, Throwable cause ) {
		super( message, cause );
	}
}
