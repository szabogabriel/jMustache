package com.szabogabriel.template.content;

import java.util.Map;

import com.szabogabriel.template.TemplateHolder;

public class StaticContent extends Content{
	
	private final String VALUE;
	
	public StaticContent(String value, TemplateHolder templateHolder) {
		super(templateHolder);
		this.VALUE = value;
	}

	@Override
	public String generateContent(Map<String, Object> values) {
		return VALUE;
	}
	
	

}
