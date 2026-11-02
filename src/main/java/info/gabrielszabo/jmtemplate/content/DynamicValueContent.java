package info.gabrielszabo.jmtemplate.content;

import java.util.Map;

import info.gabrielszabo.jmtemplate.TemplateHolder;

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
		String ret = values.getOrDefault(KEY, "").toString();
		if (rewrite) {
			ret = escapeHtml(ret);
		}
		return ret;
	}

	private String escapeHtml(String input) {
		StringBuilder ret = new StringBuilder(input.length());
		char c;
		for (int i = 0; i < input.length(); i++) {
			c = input.charAt(i);
			switch (c) {
				case '<': ret.append("&lt;"); break;
				case '>': ret.append("&gt;"); break;
				default: ret.append(c); break;
			}
		}
		return ret.toString();
	}

}
