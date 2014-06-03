package eu.vytenis.namesparser;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class NamesReader {
	private final InputStream input;
	private final List<String> allNames = new ArrayList<String>();
	private final List<String> invalidNames = new ArrayList<String>();
	private Document document;
	private Elements all;
	private List<Elements> invalid = new ArrayList<Elements>();

	public NamesReader(InputStream input) {
		this.input = input;
	}

	public NamesReader execute() {
		loadDocument();
		loadElements();
		loadInvalidElements();
		fillNames();
		fillInvalidNames();
		return this;
	}

	private void loadDocument() {
		try {
			document = Jsoup.parse(input, "UTF-8", ".");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void loadElements() {
		all = document.select(".namesList .fName, .namesList .mName");
	}

	private void loadInvalidElements() {
		invalid.add(document
				.select(".namesList .vengtini .fName, .namesList .vengtini .mName"));
		invalid.add(document
				.select(".namesList .nevartotini .fName, .namesList .nevartotini .mName"));
	}

	void dump(List<String> list) {
		for (String name : list) {
			System.out.println(name);
		}
	}

	private void fillNames() {
		fillNamesList(allNames, all);
	}

	private void fillInvalidNames() {
		for (Elements els : invalid) {
			fillNamesList(invalidNames, els);
		}
	}

	private void fillNamesList(List<String> list, Elements els) {
		for (Element el : els) {
			String s = el.text();
			list.add(toName(s));
		}
	}

	private String toName(String text) {
		char[] invalid = { 227, 242, 768, 769, 771, 7869 };
		Arrays.sort(invalid);
		char[] r = new char[text.length()];
		int next = 0;
		for (char c : text.toCharArray()) {
			if (Arrays.binarySearch(invalid, c) >= 0) {
				continue;
			}
			r[next++] = c;
		}
		return new String(r, 0, next);
	}

	public Set<Character> getUniqueChars() {
		Set<Character> r = new TreeSet<Character>();
		for (String name : allNames) {
			for (char c : toName(name).toCharArray()) {
				r.add(c);
			}
		}
		return r;
	}

	public List<String> getNames() {
		List<String> r = new ArrayList<String>(allNames);
		r.removeAll(invalidNames);
		return Collections.unmodifiableList(r);
	}

	public List<String> getAllNames() {
		return Collections.unmodifiableList(allNames);
	}

	public List<String> getInvalidNames() {
		return Collections.unmodifiableList(invalidNames);
	}

}
