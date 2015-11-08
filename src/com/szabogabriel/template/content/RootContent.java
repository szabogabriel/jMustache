package com.szabogabriel.template.content;

import java.util.List;
import java.util.Map;

import com.szabogabriel.template.TemplateHolder;

public class RootContent extends Content {
	
	private final List<Content> CONTENTS;
	
	public RootContent(String content, TemplateHolder templateHolder) {
		super(templateHolder);
		this.CONTENTS = compile(content);
	}
	
	@Override
	public String generateContent(Map<String, Object> values) {
		StringBuilder sb = new StringBuilder();
		for (Content it : CONTENTS) {
			sb.append(it.generateContent(values));
		}
		return sb.toString();
	}

}
