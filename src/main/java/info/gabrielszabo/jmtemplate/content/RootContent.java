package info.gabrielszabo.jmtemplate.content;

import java.util.Map;

import info.gabrielszabo.jmtemplate.TemplateHolder;

public class RootContent extends Content {
	
	private final Content[] CONTENTS;
	
	public RootContent(String content, TemplateHolder templateHolder) {
		super(templateHolder);
		this.CONTENTS = compile(content).toArray(Content[]::new);
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
