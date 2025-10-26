package com.jmtemplate;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jmtemplate.content.RootContent;

public class Template implements TemplateHolder {
	
	private final File TEMPLATE;
	private final String TEMPLATE_CONTENT;
	private final Boolean UPDATE;
	private final File ROOT;
	private final URL ROOT_URL;
	private final List<TemplateTimestampStructure> IMPORTED_TEMPLATES = new ArrayList<>();
	private long timestamp = -1;
	
	private RootContent rootContent;
	
	public Template(URL root, String templateName) {
		UPDATE = Boolean.FALSE;
		ROOT = null;
		ROOT_URL = root;
		TEMPLATE = createTemplateFileFromName(templateName);
		TEMPLATE_CONTENT = null;
		updateTemplate();
	}
	
	public Template(File folder, String templateName) {
		this(folder, templateName, true);
	}
	
	public Template(File folder, String templateName, Boolean updateTemplate) {
		UPDATE = updateTemplate;
		ROOT = folder;
		ROOT_URL = null;
		TEMPLATE = createTemplateFileFromName(templateName);
		TEMPLATE_CONTENT = null;
		updateTemplate();
	}
	
	public Template(String content) {
		UPDATE = false;
		TEMPLATE = null;
		ROOT = null;
		ROOT_URL = null;
		TEMPLATE_CONTENT = content;
		updateTemplate();
	}
	
	public boolean isTemplateChanged() {
		boolean ret = false;
		if (UPDATE) {
			ret = TEMPLATE.lastModified() != timestamp;
			for (TemplateTimestampStructure it : IMPORTED_TEMPLATES) {
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
		IMPORTED_TEMPLATES.add(new TemplateTimestampStructure(file));
	}
	
	@Override
	public File createTemplateFileFromName(String templateName) {
		if (ROOT != null) {
			return new File(ROOT.getAbsolutePath() + "/" + templateName);
		}
		if (ROOT_URL != null) {
			File tmp = Tools.saveUrlToTempFile(templateName, ROOT_URL);
			return tmp;
		}
		return null;
	}
	
	private class TemplateTimestampStructure {
		private final File TEMPLATE_FILE;
		private Long timestamp;
		
		public TemplateTimestampStructure(File templateFile) {
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
