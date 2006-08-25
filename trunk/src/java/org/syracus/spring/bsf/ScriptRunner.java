package org.syracus.spring.bsf;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;

public class ScriptRunner {

	private ScriptLoader scriptLoader;

	public ScriptLoader getScriptLoader() {
		return scriptLoader;
	}

	public void setScriptLoader(ScriptLoader scriptLoader) {
		this.scriptLoader = scriptLoader;
	}
	
	public Object eval( String source ) {
		Script script = getScriptLoader().getScript( source );
		
		BSFManager manager = new BSFManager();
		try {
			Object ret = manager.eval(
					script.getLanguage(),
					script.getSource().getAbsolutePath(),
					0, 0,
					script.getCode()
			);
			
			return( ret );
		} catch( BSFException e ) {
			throw new ScriptException( "Script execution failed.", e );
		}
	}
	
	public void exec( String source ) {
		Script script = getScriptLoader().getScript( source );
		BSFManager manager = new BSFManager();
		try {
			manager.exec(
					script.getLanguage(),
					script.getSource().getAbsolutePath(),
					0, 0,
					script.getCode()
			);
		} catch( BSFException e ) {
			throw new ScriptException( "Script execution failed.", e );
		}
	}
}
