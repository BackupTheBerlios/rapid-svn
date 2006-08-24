package org.syracus.spring.bsf;

import org.springframework.core.NestedRuntimeException;

public class ScriptException extends NestedRuntimeException {

	private static final long serialVersionUID = -838675604807206341L;

	public ScriptException( String message ) {
		super( message );
	}
	
	public ScriptException( String message, Throwable cause ) {
		super( message, cause );
	}
	
}
