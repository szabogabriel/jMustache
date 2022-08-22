package com.jmtemplate.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.jmtemplate.Template;

public class RecursiveValueTest {
	
	@Test
	public void testRecursiveValues() {
		Map<String, Object> data = new HashMap<>();
		data.put("locale", "en");
		data.put("postfix", "postfix");
		data.put("_en.name.postfix", "yes, it works.");
		data.put("_en.age.postfix", "55.");
		
		Template t = new Template(new File("./test"), "test9.template");
		String expected = "* yes, it works.\n* 55.";
		
		String ret = t.render(data);
		System.out.println(ret);
		assertEquals(expected, ret);
	}

}
