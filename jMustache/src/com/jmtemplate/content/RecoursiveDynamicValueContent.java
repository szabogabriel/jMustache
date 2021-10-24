package com.jmtemplate.content;

import java.util.List;
import java.util.Map;

import com.jmtemplate.TemplateHolder;

public class RecoursiveDynamicValueContent extends Content {

	private final List<Content> CONTENTS;
	private final boolean rewrite;
	
	public RecoursiveDynamicValueContent(String content, boolean rewrite, TemplateHolder templateHolder) {
		super(templateHolder);
		this.rewrite = rewrite;
		this.CONTENTS = compile(content);
	}

	@Override
	public String generateContent(Map<String, Object> values) {
		String key = generateKey(values);
		String ret = values.containsKey(key) ? values.get(key).toString() : "";
		if (rewrite) {
			ret = ret.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		}
		return ret;
	}
	
	private String generateKey(Map<String, Object> values) {
		StringBuilder sb = new StringBuilder();
		for (Content it : CONTENTS) {
			sb.append(it.generateContent(values));
		}
		return sb.toString();
	}

}
