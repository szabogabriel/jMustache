package com.jmtemplate.content;

import java.util.Map;

import com.jmtemplate.TemplateHolder;

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
