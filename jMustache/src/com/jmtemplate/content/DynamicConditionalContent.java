package com.jmtemplate.content;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jmtemplate.TemplateCallback;
import com.jmtemplate.TemplateHolder;

public class DynamicConditionalContent extends Content {
	
	private final String KEY;
	private final List<Content> CONTENTS;
	private final boolean EMPTY; 
	
	public DynamicConditionalContent(String key, String content, TemplateHolder templateHolder) {
		this(key, content, false, templateHolder);
	}
	
	public DynamicConditionalContent(String key, String content, boolean empty, TemplateHolder templateHolder) {
		super(templateHolder);
		this.EMPTY = empty;
		this.KEY = key;
		this.CONTENTS = compile(content);
	}
	
	@Override
	public String generateContent(Map<String, Object> values) {
		String ret = "";
		if (values.containsKey(KEY) || EMPTY) {
			ret = handle(values);
		}
		return ret;
	}
	
	@SuppressWarnings("unchecked")
	private String handle(Map<String, Object> values) {
		StringBuilder sb = new StringBuilder();
		if (EMPTY) {
			sb.append(handleEmpty(values));
		} else
		if (values.containsKey(KEY)) { 
			Object k = values.get(KEY);
			if (k instanceof List) {
				Map<String, Object> swp = new HashMap<String, Object>();
				List<Map<String, Object>> valuesList = (List<Map<String, Object>>)k;
				for (Map<String, Object> it : valuesList) {
					for (Content it2 : CONTENTS) {
						swp.putAll(values);
						swp.putAll(it);
						sb.append(it2.generateContent(swp));
						swp.clear();
					}
				}
			} else 
			if (k instanceof Map) {
				Map<String, Object> swp = (Map<String, Object>)k;
				swp.putAll(values);
				for (Content it : CONTENTS) {
					sb.append(it.generateContent(swp));
				}
			} else
			if (k instanceof TemplateCallback) {
				StringBuilder localSB = new StringBuilder();
				for (Content it : CONTENTS) {
					localSB.append(it.generateContent(values));
				}
				final String line = localSB.toString();
				final String [] lineBreak = line.split("\\s+");
				sb.append(((TemplateCallback) k).run(Arrays.asList(lineBreak)));
			} else {
				if (k instanceof Boolean && ((Boolean)k).booleanValue()) {
					for (Content it : CONTENTS) {
						sb.append(it.generateContent(values));
					}
				}
			}
		}
		return sb.toString();
	}
	
	private String handleEmpty(Map<String, Object> values) {
		Object k = values.get(KEY);
		StringBuilder ret = new StringBuilder();
		if (	!values.containsKey(KEY) ||
				values.get(KEY) == null ||
				(k instanceof Boolean && ((Boolean)k) == Boolean.FALSE) ||
				(k instanceof List && ((List<?>)k).size() == 0) 
			) {
			for (Content it : CONTENTS) {
				ret.append(it.generateContent(values));
			}
		}
		return ret.toString();
	}

}
