package org.syracus.spring.bsf;

import org.apache.bsf.BSFManager;


public class ScriptEngineRegistry {

	private ScriptEngine[] engines;
	
	
	public ScriptEngine[] getEngines() {
		return engines;
	}


	public void setEngines(ScriptEngine[] engines) {
		this.engines = engines;
	}


	public void afterPropertiesSet() throws Exception {
		for ( ScriptEngine engine : engines ) {
			BSFManager.registerScriptingEngine(
					engine.getLanguage(),
					engine.getClassname(),
					engine.getExtensions()
			);
		}
	}
	
}
