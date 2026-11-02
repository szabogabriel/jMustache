package info.gabrielszabo.jmtemplate.test;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import info.gabrielszabo.jmtemplate.Template;

public class RecursiveValueTest {
	
	@Test
	public void testRecursiveValues() {
		Map<String, Object> data = new HashMap<>();
		data.put("locale", "en");
		data.put("postfix", "postfix");
		data.put("_en.name.postfix", "yes, it works.");
		data.put("_en.age.postfix", "55.");
		
		Template t = new Template(new File("./src/test/resources/"), "test9.template");
		String expected = "* yes, it works.\n* 55.";
		
		String ret = t.render(data);
		System.out.println(ret);
		assertEquals(expected, ret);
	}

}
