package com.szabogabriel.template;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.szabogabriel.template.content.RootContent;

public class Template implements TemplateHolder {
	
	private final File TEMPLATE;
	private final String TEMPLATE_CONTENT;
	private final Boolean UPDATE;
	private final File ROOT;
	private final List<FileTimestampStructure> IMPORTED_TEMPLATES = new ArrayList<>();
	private long timestamp = -1;
	private RootContent rootContent;
	
	public Template(File folder, String templateName) {
		UPDATE = true;
		ROOT = folder;
		TEMPLATE = createTemplateFileFromName(templateName);
		TEMPLATE_CONTENT = null;
		updateTemplate();
	}
	
	public Template(File folder, String templateName, Boolean updateTemplate) {
		UPDATE = updateTemplate;
		ROOT = folder;
		TEMPLATE = createTemplateFileFromName(templateName);
		TEMPLATE_CONTENT = null;
		updateTemplate();
	}
	
	public Template(String content) {
		UPDATE = false;
		TEMPLATE = null;
		ROOT = null;
		TEMPLATE_CONTENT = content;
		updateTemplate();
	}
	
	public boolean isTemplateChanged() {
		boolean ret = false;
		if (UPDATE) {
			ret = TEMPLATE.lastModified() != timestamp;
			for (FileTimestampStructure it : IMPORTED_TEMPLATES) {
				if (!ret) {
					ret = it.isTemplateChanged();
				}
			}
		}
		return ret;
	}
	
	private String getTemplateContent() {
		return (TEMPLATE_CONTENT == null) ? Tools.readFile(TEMPLATE) : TEMPLATE_CONTENT;
	}
	
	private void updateTemplate() {
		IMPORTED_TEMPLATES.clear();
		rootContent = new RootContent(getTemplateContent(), this);
		timestamp = TEMPLATE.lastModified();
	}
	
	public String render(Map<String, Object> values) {
		if (isTemplateChanged()) {
			updateTemplate();
		}
		return rootContent.generateContent(values);
	}

	@Override
	public void addTemplateFile(File file) {
		IMPORTED_TEMPLATES.add(new FileTimestampStructure(file));
	}
	
	@Override
	public File createTemplateFileFromName(String templateName) {
		return new File(ROOT.getAbsolutePath() + "/" + templateName);
	}
	
	
	
	private class FileTimestampStructure {
		private final File TEMPLATE_FILE;
		private Long timestamp;
		
		public FileTimestampStructure(File templateFile) {
			this.TEMPLATE_FILE = templateFile;
			updateTimestamp();
		}
		
		public boolean isTemplateChanged() {
			return timestamp != TEMPLATE_FILE.lastModified();
		}
		
		public void updateTimestamp() {
			this.timestamp = TEMPLATE_FILE.lastModified();
		}
	}


}
