package org.syracus.spring.bsf;

public class ScriptEngine {

	private String language;
	private String classname;
	private String[] extensions;
	
	public ScriptEngine() {
	}
	
	public ScriptEngine( String classname, String language, String[] extensions ) {
		setClassname( classname );
		setLanguage( language );
		setExtensions( extensions );
	}
	
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
	public void setLanguage(String language) {
		this.language = language;
	}
	
	
}
