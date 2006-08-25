package org.syracus.spring.bsf;

import java.util.List;

import org.apache.bsf.BSFManager;
import org.springframework.beans.factory.InitializingBean;

public class ScriptEngineRegistry implements InitializingBean {

	private List<ScriptEngine> scriptEngines;

	public List<ScriptEngine> getScriptEngines() {
		return scriptEngines;
	}

	public void setScriptEngines(List<ScriptEngine> scriptEngines) {
		this.scriptEngines = scriptEngines;
	}

	public void afterPropertiesSet() throws Exception {
		for ( ScriptEngine engine : this.scriptEngines ) {
			BSFManager.registerScriptingEngine(
					engine.getLanguage(),
					engine.getClassname(),
					engine.getExtensions()
			);
		}
	}

}
