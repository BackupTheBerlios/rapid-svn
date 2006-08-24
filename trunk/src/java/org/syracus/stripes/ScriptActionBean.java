package org.syracus.stripes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.syracus.spring.bsf.IScriptLoader;
import org.syracus.spring.bsf.Script;
import org.syracus.spring.bsf.ScriptException;

public abstract class ScriptActionBean implements ActionBean {

	protected IScriptLoader scriptLoader;
	private String script;
	
	
	public IScriptLoader getScriptLoader() {
		return scriptLoader;
	}
	
	public void setScriptLoader( IScriptLoader scriptLoader ) {
		this.scriptLoader = scriptLoader;
	}
	
	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public Resolution exec() {
		try {
			Script script = getScriptLoader().getScript( getScript() );
			
			BSFManager manager = new BSFManager();
			prepare( manager );
			
			manager.exec( 
					script.getLanguage(),
					script.getSource().getAbsolutePath(),
					0, 0,
					script.getCode()
			);
			return( getContext().getSourcePageResolution() );
		} catch( BSFException e ) {
			throw new ScriptException( "ERROR", e );
		}
	}
	
	@DefaultHandler
	public Resolution eval() {
		try {
			Script script = getScriptLoader().getScript( getScript() );
			
			BSFManager manager = new BSFManager();
			prepare( manager );
			
			Object result = manager.eval( 
					script.getLanguage(),
					script.getSource().getAbsolutePath(),
					0, 0,
					script.getCode()
			);
			if ( result instanceof Resolution ) {
				return( (Resolution)result );
			} else {
				return( getContext().getSourcePageResolution() );
			}
		} catch( BSFException e ) {
			throw new ScriptException( "ERROR", e );
		}
	}
	
	protected void prepare( BSFManager manager )
		throws BSFException
	{
		manager.declareBean( "request", getContext().getRequest(), HttpServletRequest.class );
		manager.declareBean( "response", getContext().getResponse(), HttpServletResponse.class );
		manager.declareBean( "application", getContext().getRequest().getSession().getServletContext(), ServletContext.class );
		manager.declareBean( "actionContext", getContext(), getContext().getClass() );
	}

}
