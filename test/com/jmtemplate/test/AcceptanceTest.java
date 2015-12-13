package com.jmtemplate.test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.jmtemplate.Template;
import com.jmtemplate.TemplateCallback;

public class AcceptanceTest {
	
	@Test
	public void testVariable() {
		System.out.println("Test: Variable");
		final Map<String, Object> map = new HashMap<>();
		map.put("name", "Chris");
		map.put("company", "<b>GitHub</b>");
		
		final String toAchieve = "* Chris\n* \n* &lt;b&gt;GitHub&lt;/b&gt;\n* <b>GitHub</b>";
		
		final Template t = new Template(new File("./test/"), "test1.template");
		final String rendered = t.render(map);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered: \n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}
	
	@Test
	public void testSectionTrue() {
		System.out.println("Test: Section: True");
		final Map<String, Object> map = new HashMap<>();
		map.put("person", Boolean.TRUE);
		
		final String toAchieve = "Shown.\n\n  Never shown!\n";
		
		final Template t = new Template(new File("./test/"), "test2.template");
		final String rendered = t.render(map);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered: \n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}
	
	@Test
	public void testSectionFalse() {
		System.out.println("Test: Section: False");
		final Map<String, Object> map = new HashMap<>();
		map.put("person", Boolean.FALSE);
		
		final String toAchieve = "Shown.\n";
		
		final Template t = new Template(new File("./test/"), "test2.template");
		final String rendered = t.render(map);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered: \n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}
	
	@Test
	public void testNonEmptyList() {
		System.out.println("Test: NonEmptyList");
		final Map<String, Object> map = new HashMap<>();
		final Map<String, Object> map1 = new HashMap<>();
		final Map<String, Object> map2 = new HashMap<>();
		final Map<String, Object> map3 = new HashMap<>();
		final List<Map<String, Object>> list = new ArrayList<>();
		
		map1.put("name", "resque");
		map2.put("name", "hub");
		map3.put("name", "rip");
		
		list.add(map1);
		list.add(map2);
		list.add(map3);
		
		map.put("repo", list);
		
		final String toAchieve = "\n  <b>resque</b>\n  <b>hub</b>\n  <b>rip</b>";
		
		final Template t = new Template(new File("./test/"), "test3.template");
		final String rendered = t.render(map);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered: \n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}
	
	@Test
	public void testNonEmptyValues() {
		System.out.println("Test: nonEmptyValues");
		final Map<String, Object> map = new HashMap<>();
		final Map<String, Object> map1 = new HashMap<>();
		
		map1.put("name", "Jon");
		map.put("person?", map1);
		
		final String toAchieve = "\n  Hi Jon!\n";
		
		final Template t = new Template(new File("./test/"), "test4.template");
		final String rendered = t.render(map);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered: \n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}
	
	@Test
	public void testInvertedSection() {
		System.out.println("Test: invertedSection");
		final Map<String, Object> map = new HashMap<>();
		map.put("repoa", new ArrayList<>());
		map.put("repob", Boolean.FALSE);
		
		final String toAchieve = "No repoa\nNo repob\nNo repoc";
		
		final Template t = new Template(new File("./test/"), "test5.template");
		final String rendered = t.render(map);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered:\n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}
	
	@Test
	public void testComment() {
		System.out.println("Test: comment");
		
		final String toAchieve = "<h1>Today.</h1>";
		
		final Template t = new Template(new File("./test/"), "test6.template");
		final String rendered = t.render(null);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered:\n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}
	
	@Test
	public void testPartial() {
		System.out.println("Test: partials");
		final Map<String, Object> map = new HashMap<>();
		final Map<String, Object> map1 = new HashMap<>();
		final List<Map<String, Object>> list = new ArrayList<>();
		map1.put("name", "theName");
		list.add(map1);
		map.put("names", list);
		
		final String toAchieve = "<h2>Names</h2>\n<strong>theName</strong>";
		
		final Template t = new Template(new File("./test/"), "test7a.template");
		final String rendered = t.render(map);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered:\n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}
	
	@Test
	public void testWeb() {
		System.out.println("Test: web");
		final Map<String, Object> map = new HashMap<>();
		final Map<String, Object> map1 = new HashMap<>();
		final List<Map<String, Object>> list = new ArrayList<>();
		map1.put("name", "theName");
		list.add(map1);
		map.put("names", list);
		
		final String toAchieve = "<h2>Names</h2>\n<strong>theName</strong>";
		
		try {
			URL url = new URL("http://localhost/test");
			
			final Template t = new Template(url, "test7a.template");
			final String rendered = t.render(map);
			
			System.out.println("To achieve:\n" + toAchieve);
			System.out.println("Rendered:\n" + rendered);
			System.out.println("-------------------\n");
			
			Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testLambda() {
		System.out.println("Test: lambdas");
		final Map<String, Object> map = new HashMap<>();
		map.put("name", "World");
		map.put("lambda", new TemplateCallback(){
			@Override
			public String run(List<String> args) {
				String ret = "";
				for (String it : args) {
					ret = it + " " + ret;
				}
				return ret.trim();
			}
		});
		
		final String toAchieve = "Hello, World";
		
		final Template t = new Template(new File("./test/"), "test8.template");
		final String rendered = t.render(map);
		
		System.out.println("To achieve:\n" + toAchieve);
		System.out.println("Rendered:\n" + rendered);
		System.out.println("-------------------\n");
		
		Assert.assertArrayEquals(toAchieve.toCharArray(), rendered.toCharArray());
	}

}
