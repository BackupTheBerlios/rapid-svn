package org.syracus.spring.bsf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScriptCacheLoader extends ScriptLoader {

	private boolean reload = true;
	private boolean enabled = true;
	protected Map<String,Script> cache = new HashMap<String,Script>();

	
	public boolean isEnabled() {
		return enabled;
	}


	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	public boolean isReload() {
		return reload;
	}


	public void setReload(boolean reload) {
		this.reload = reload;
	}


	@Override
	public Script getScript(String source) {
		Script script = null;
		if ( true == isEnabled() ) {
			script = this.cache.get( source );
			if ( null == script ) {
				if ( null != ( script = super.getScript( source ) ) ){
					this.cache.put( source, script );
				}
			} else if ( true == isReload() ) {
				long lm = script.getSource().lastModified();
				if ( lm > script.getModified() ) {
					try {
						script.setCode( super.readFile( script.getSource() ) );
						script.setModified( lm );
					} catch( IOException e ) {
						throw new ScriptException( "Failed to load script.", e );
					}
				}
			}
			return( script );
		} else {
			script = super.getScript( source );
		}
		return( script );
	}
	
}
