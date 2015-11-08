package com.szabogabriel.template.content;

import java.util.Map;

import com.szabogabriel.template.TemplateHolder;

public class DynamicValueContent extends Content {
	
	private final String KEY;
	private final boolean rewrite;
	
	public DynamicValueContent(String key, boolean rewrite, TemplateHolder templateHolder) {
		super(templateHolder);
		this.rewrite = rewrite;
		this.KEY = key;
	}

	@Override
	public String generateContent(Map<String, Object> values) {
		String ret = values.containsKey(KEY) ? values.get(KEY).toString() : "";
		if (rewrite) {
			ret = ret.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		}
		return ret;
	}

}
