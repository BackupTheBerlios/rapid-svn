package org.syracus.spring.bsf;

import org.springframework.core.NestedRuntimeException;

public class ScriptException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;

	public ScriptException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ScriptException(String arg0) {
		super(arg0);
	}

	
}
