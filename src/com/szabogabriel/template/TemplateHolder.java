package com.szabogabriel.template;

import java.io.File;

public interface TemplateHolder {
	
	public void addTemplateFile(File file);
	
	public File createTemplateFileFromName(String templateName);

}
