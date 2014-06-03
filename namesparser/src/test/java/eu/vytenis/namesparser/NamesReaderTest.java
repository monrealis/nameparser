package eu.vytenis.namesparser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

public class NamesReaderTest {
	private static final List<String> EMPTY = Collections.<String> emptyList();

	@Test
	public void namesAreNotEmpty() {
		assertNotNull(execute().getAllNames());
		assertNotEquals(EMPTY, execute().getAllNames());
	}

	@Test
	public void invalidNamesAreNotEmpty() {
		assertNotNull(execute().getInvalidNames());
		assertNotEquals(EMPTY, execute().getInvalidNames());
	}

	@Test
	public void invalidNamesContainAlgementasAdolf() {
		assertTrue(execute().getInvalidNames().contains("Algemantas"));
		assertTrue(execute().getInvalidNames().contains("Adolf"));
	}

	@Test
	public void namesContainArunas() {
		assertTrue(execute().getNames().contains("Arūnas"));
	}

	@Test
	public void namesAreDifferentFromAllNames() {
		assertNotEquals(execute().getNames(), execute().getAllNames());
	}

	@Test
	public void namesContainOnlyLtLetters() {
		Set<Character> set = new TreeSet<Character>(execute().getUniqueChars());
		CharUtils.removeLtLetters(set);
		CharUtils.dump(set);
		assertEquals(Collections.emptySet(), set);
	}

	private NamesReader execute() {
		return create().execute();
	}

	private NamesReader create() {
		try {
			return safeCreate();
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private NamesReader safeCreate() throws FileNotFoundException {
		InputStream is = getClass().getResourceAsStream("/input/a.html");
		return new NamesReader(is);
	}

}
