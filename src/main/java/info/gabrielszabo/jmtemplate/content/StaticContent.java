package info.gabrielszabo.jmtemplate.content;

import java.util.Map;

import info.gabrielszabo.jmtemplate.TemplateHolder;

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
