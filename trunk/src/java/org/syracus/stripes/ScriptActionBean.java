package org.syracus.stripes;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.Resolution;

import org.apache.bsf.BSFException;
import org.apache.bsf.BSFManager;
import org.apache.commons.beanutils.PropertyUtils;
import org.syracus.spring.bsf.Script;
import org.syracus.spring.bsf.ScriptException;
import org.syracus.spring.bsf.ScriptLoader;

/**
 * Abstract base class for all scripted actions.
 * Usage:
 * 		Inherit for your concrete implementation
 * 
 * 		@UrlBinding("/script.action")
 * 		public class MyAction extends ScriptActionBean {
 *
 * 			//
 * 			// Apply your own ApplicationContext implementation
 * 			// as described in BestPractice section on stripes homepage.
 * 			//
 * 			private MyApplicationContext applicationContext;
 * 
 * 			public MyApplicationContext getApplicationContext() {
 * 				return( this.applicationContext );
 * 			}
 * 			public void setApplicationContext( ApplicationContext context ) {
 * 				this.applicationContext = (MyApplicationContext)context;
 * 			}
 * 
 * 			//
 * 			// All you need to implements is getScriptLoader()
 * 			// As the ScriptLoader is part of a module extending spring
 * 			// you should use stripes SpringInterceptor to inject an instance
 * 			// of this class.
 * 			//
 * 			private ScriptLoader scriptLoader;
 * 
 * 			public ScriptLoader getScriptLoader() {
 * 				return( this.scriptLoader );
 * 			}
 * 			@SpringBean("cachingScriptLoader")
 * 			public void setScriptLoader( ScriptLoader scriptLoader ) {
 * 				this.scriptLoader = scriptLoader;
 * 			}
 * 
 * 			//
 * 			// Finally declare all properties as for all other stripes actions.
 * 			// If you want to have them exposed to your scripting context you
 * 			// could use the ScriptBean annotation. Using the optional value
 * 			// you can specify the name under which the bean will be accessible
 * 			// in your scripts.
 * 			//
 * 			@ScriptBean("myUserVariable")
 * 			private User user;
 * 
 * 			public void setUser( User user ) {
 * 				this.user = user;
 * 			}
 * 			public User getUser() {
 * 				return( this.user );
 * 			}
 * 			
 * 		}
 * 
 * The basic implementation provides two events by default.
 * eval() (@DefaultHandler) and exec(). The difference is, that evaluated scripts
 * may return a value (in stripes scope it's likly that this will be a resolution).
 * If no return value is passed the eval() event will behave like the exec() event
 * and use the sourcePageResolution for forwarding.
 * 
 * Calling the action is simple now
 * 
 * \/script.action?eval=&source=/pathtoyourscript/script.bsh[&any additional request paramteters]
 * @author snwiem
 *
 */
public abstract class ScriptActionBean implements ActionBean {

	private String source;
	
	public abstract ScriptLoader getScriptLoader();

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}
	
	@DefaultHandler
	public Resolution eval() {
		Script script = getScript();
		try {
			Object result = createManager().eval(
					script.getLanguage(),
					script.getSource().getAbsolutePath(),
					0,
					0,
					script.getCode()
			);
			
			if ( result instanceof Resolution ) {
				return( (Resolution)result );
			} else {
				return( getContext().getSourcePageResolution() );
			}
		} catch( BSFException e ) {
			throw new ScriptException( "Failed to execute script '" + getSource() + "'", e );
		} catch( IllegalAccessException e ) {
			throw new ScriptException( "Failed to execute script '" + getSource() + "'", e );
		} catch( InvocationTargetException e ) {
			throw new ScriptException( "Failed to execute script '" + getSource() + "'", e );
		} catch( NoSuchMethodException e ) {
			throw new ScriptException( "Failed to execute script '" + getSource() + "'", e );
		}
		
	}
	
	public Resolution exec() {
		Script script = getScript();
		try {
			createManager().exec(
					script.getLanguage(),
					script.getSource().getAbsolutePath(),
					0,
					0,
					script.getCode()
			);
			return( getContext().getSourcePageResolution() );
		} catch( BSFException e ) {
			throw new ScriptException( "Failed to execute script '" + getSource() + "'", e );
		} catch( IllegalAccessException e ) {
			throw new ScriptException( "Failed to execute script '" + getSource() + "'", e );
		} catch( InvocationTargetException e ) {
			throw new ScriptException( "Failed to execute script '" + getSource() + "'", e );
		} catch( NoSuchMethodException e ) {
			throw new ScriptException( "Failed to execute script '" + getSource() + "'", e );
		}
	}
	
	protected Script getScript() {
		if ( null == getSource() ) {
			throw new ScriptException( "Missing source parameter for current request." );
		}
		Script script = getScriptLoader().getScript( getSource() );
		if ( null == script ) {
			throw new ScriptException( "No script found for source parameter '" + getSource() + "'" );
		}
		return( script );
	}
	
	protected BSFManager createManager()
		throws BSFException, IllegalAccessException, InvocationTargetException, NoSuchMethodException
	{
		BSFManager manager = new BSFManager();
		
		// declare action bean context
		manager.declareBean( "context", getContext(), getContext().getClass() );
		
		// scan current bean instance for ScriptBean annotations of bean properties to be exposed to the script.
		for ( Field f : getClass().getDeclaredFields() ) {
			if ( f.isAnnotationPresent( ScriptBean.class ) ) {
				String name = f.getAnnotation( ScriptBean.class ).value();
				
				if ( null == name || name.trim().equals( "" ) ) {
					name = f.getName();
				}

				Object value = PropertyUtils.getProperty( this, f.getName() );
				Class type = f.getType();

				manager.declareBean(
					name,
					value,
					type
				);
				
			}
		}
		return( manager );
	}

}
