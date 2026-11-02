package info.gabrielszabo.jmtemplate.content;

import java.util.Map;

import info.gabrielszabo.jmtemplate.TemplateHolder;

public class RecoursiveDynamicValueContent extends Content {

	private final Content[] CONTENTS;
	private final boolean rewrite;
	
	public RecoursiveDynamicValueContent(String content, boolean rewrite, TemplateHolder templateHolder) {
		super(templateHolder);
		this.rewrite = rewrite;
		this.CONTENTS = compile(content).toArray(Content[]::new);
	}

	@Override
	public String generateContent(Map<String, Object> values) {
		String key = generateKey(values);
		String ret = values.getOrDefault(key, "").toString();
		if (rewrite) {
			ret = escapeHtml(ret);
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
