package eu.vytenis.namesparser;

import static org.junit.Assert.assertTrue;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

public class MultiNamesReaderTest {

	@Test
	public void printNames() {
		String names = StringUtils.join(execute().getNames(), "\n");
		System.out.println(names);
	}

	@Test
	public void namesContainVytenis() {
		assertTrue(execute().getNames().contains("Vytenis"));
	}

	private MultiNamesReader execute() {
		MultiNamesReader r = MultiNamesReader.create(
				"src/main/resources/input", "html");
		r.execute();
		return r;
	}
}
