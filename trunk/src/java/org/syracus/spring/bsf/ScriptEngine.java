package org.syracus.spring.bsf;

public class ScriptEngine {

	private String language;
	private String classname;
	private String[] extensions;
	
	public String getClassname() {
		return classname;
	}
	public void setClassname(String classname) {
		this.classname = classname;
	}
	public String[] getExtensions() {
		return extensions;
	}
	public void setExtensions(String[] extensions) {
		this.extensions = extensions;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String lang) {
		this.language = lang;
	}
	
}
