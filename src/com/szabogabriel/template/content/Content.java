package com.szabogabriel.template.content;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.szabogabriel.template.TemplateHolder;
import com.szabogabriel.template.Tools;

public abstract class Content {
	
	public abstract String generateContent(Map<String, Object> values);
	private String content;
	private int i;
	
	private final TemplateHolder TEMPLATE_HOLDER;
	
	public Content(TemplateHolder templateHolder) {
		this.TEMPLATE_HOLDER = templateHolder;
	}
	
	protected List<Content> compile(String content) {
		this.content = content;
		return compile();
	}
	
	private boolean isNext() {
		return i < content.length();
	}
	
	private boolean isTagStart() {
		return content.charAt(i) == '{' && content.charAt(i + 1) == '{';
	}
	
	private boolean isDynamicValue() {
		return content.charAt(i) == '#';
	}
	
	private boolean isNegation() {
		return content.charAt(i) == '^';
	}
	
	private boolean isComment() {
		return content.charAt(i) == '!';
	}
	
	private boolean isImport() {
		return content.charAt(i) == '>';
	}
	
	private Content handleDynamicValueWithoutRewrite() {
		i++;
		String value = content.charAt(i++) + ""; 
		while (content.charAt(i) != '}') 
			value += content.charAt(i++); 
		i += 2;
		String endtag = "{{/" + value + "}}";
		String cc = content.charAt(i++) + ""; 
		while (!content.substring(i, i + endtag.length()).equals(endtag)) 
			cc += content.charAt(i++);
		i += endtag.length();
		return new DynamicConditionalContent(value, cc, TEMPLATE_HOLDER);
	}
	
	private Content handleDynamicValue() {
		boolean rewrite = true;
		if (content.charAt(i) == '{') {
			rewrite = false;
			i++;
		}
		String value = content.charAt(i++) + "";
		while (content.charAt(i) != '}') 
			value += content.charAt(i++);
		i += 2 + (rewrite ? 0 : 1);
		return new DynamicValueContent(value, rewrite, TEMPLATE_HOLDER);
	}
	
	private Content handleNegation() {
		i++;
		String value = content.charAt(i++) + "";
		while (content.charAt(i) != '}')
			value += content.charAt(i++);
		i += 2;
		String endtag = "{{/" + value + "}}";
		String cc = content.charAt(i++) + "";
		while (!content.substring(i, i + endtag.length()).equals(endtag))
			cc += content.charAt(i++);
		i += endtag.length();
		return new DynamicConditionalContent(value, cc, true, TEMPLATE_HOLDER);
	}
	
	private void handleComment() {
		while (content.charAt(i++) != '}');
		i++;
	}
	
	private Content handleImport() {
		i++;
		String fileName = content.charAt(i++) + "";
		while (content.charAt(i) != '}')
			fileName += content.charAt(i++);
		i += 2;
		File f = TEMPLATE_HOLDER.createTemplateFileFromName(fileName);
		TEMPLATE_HOLDER.addTemplateFile(f);
		String fileContent = Tools.readFile(f);
		return new RootContent(fileContent, TEMPLATE_HOLDER);
	}
	
	private Content handleLeftoverStaticContent(String line) {
		Content ret = null;
		if (line.length() > 0) {
			ret = new StaticContent(line, TEMPLATE_HOLDER);
		}
		return ret;
	}
	
	private List<Content> compile() {
		List<Content> ret = new ArrayList<Content>(); 
		i = 0;
		StringBuilder sb = new StringBuilder();
		while (isNext()) {
			if (isTagStart()) {
				Content toAdd = handleLeftoverStaticContent(sb.toString());
				if (toAdd != null) ret.add(toAdd);
				i += 2;
				sb = new StringBuilder();
				
				if (isDynamicValue()) {
					ret.add(handleDynamicValueWithoutRewrite());
				} else 
				if (isNegation()) {
					ret.add(handleNegation());
				} else
				if (isComment()) {
					handleComment();
				} else
				if (isImport()) {
					ret.add(handleImport());
				} else {
					ret.add(handleDynamicValue());
				}
			} else {
				sb.append(content.charAt(i++));
			}
		}
		Content toAdd = handleLeftoverStaticContent(sb.toString());
		if (toAdd != null) ret.add(toAdd);
		sb = new StringBuilder();
		return ret;
	}
	
}
