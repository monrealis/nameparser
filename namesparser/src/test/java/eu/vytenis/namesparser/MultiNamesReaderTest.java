package eu.vytenis.namesparser;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

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

	@Test
	public void namesContainOnlyLtLetters() {
		Set<Character> letters = new TreeSet<Character>(execute().getUniqueChars());
		CharUtils.removeLtLetters(letters);
		CharUtils.removeStressedLetters(letters);
		letters.removeAll(asList(' '));
		CharUtils.dump(letters);
		assertEquals(Collections.emptySet(), letters);
	}

	private MultiNamesReader execute() {
		MultiNamesReader r = MultiNamesReader.create("src/main/resources/input", "html");
		r.execute();
		return r;
	}
}
