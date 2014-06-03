package eu.vytenis.namesparser;

import java.util.Set;

public class CharUtils {
	private static final String letters = "aąbcčdeęėfghiįyjklmnoprsštuųūvzžAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽ";

	public static void removeLtLetters(Set<Character> set) {
		for (char letter : letters.toCharArray()) {
			set.remove(letter);
		}
	}

	public static void dump(Set<Character> set) {
		for (char c : set) {
			System.out.println((int) c);
		}
	}

}
