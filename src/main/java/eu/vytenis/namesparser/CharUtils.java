package eu.vytenis.namesparser;

import java.util.Set;

public class CharUtils {
	private static final String letters = "aąbcčdeęėfghiįyjklmnoprsštuųūvzžAĄBCČDEĘĖFGHIĮYJKLMNOPRSŠTUŲŪVZŽ";
	private static final String stressedLetters = "àèìĩũ";

	public static void removeLtLetters(Set<Character> set) {
		removeLetters(set, letters);
	}

	public static void removeStressedLetters(Set<Character> set) {
		removeLetters(set, stressedLetters);
	}

	private static void removeLetters(Set<Character> set, String lettersToRemove) {
		for (char letter : lettersToRemove.toCharArray())
			set.remove(letter);
	}

	public static void dump(Set<Character> set) {
		for (char c : set)
			System.out.println((int) c);
	}
}
