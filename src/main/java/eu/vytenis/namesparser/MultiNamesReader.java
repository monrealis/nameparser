package eu.vytenis.namesparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class MultiNamesReader {
	private final List<InputStream> inputs = new ArrayList<InputStream>();
	private List<NamesReader> readers = new ArrayList<NamesReader>();

	public static MultiNamesReader create(String dir, String extension) {
		try {
			return new MultiNamesReader(new File(dir), extension);
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	private MultiNamesReader(File dir, String extension)
			throws FileNotFoundException {
		for (File f : dir.listFiles()) {
			if (f.getName().endsWith("." + extension)) {
				inputs.add(new FileInputStream(f));
			}
		}
	}

	public MultiNamesReader execute() {
		fillReaders();
		executeAll();
		return this;
	}

	private void fillReaders() {
		for (InputStream is : inputs) {
			readers.add(new NamesReader(is));
		}
	}

	private void executeAll() {
		for (NamesReader r : readers) {
			r.execute();
		}
	}

	public List<String> getNames() {
		List<String> names = new ArrayList<String>();
		for (NamesReader r : readers) {
			names.addAll(r.getNames());
		}
		Collections.sort(names);
		return Collections.unmodifiableList(names);
	}

	public Set<Character> getUniqueChars() {
		Set<Character> chars = new TreeSet<Character>();
		for (NamesReader r : readers) {
			chars.addAll(r.getUniqueChars());
		}
		return chars;
	}

}
