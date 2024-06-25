package com.nikolasubasic.texttransformer.util;

import java.util.HashMap;
import java.util.Map;

public class TransliterationHelper {
    private static final Map<Character, String> transliterationMap = new HashMap<>();

    public static String getLatinEquivalent(char c) {
        return transliterationMap.getOrDefault(c, String.valueOf(c));
    }

    static {
        // Cyrillic to Latin uppercase
        transliterationMap.put('А', "A");
        transliterationMap.put('Б', "B");
        transliterationMap.put('В', "V");
        transliterationMap.put('Г', "G");
        transliterationMap.put('Д', "D");
        transliterationMap.put('Ђ', "Dj");
        transliterationMap.put('Е', "E");
        transliterationMap.put('Ж', "Z");
        transliterationMap.put('З', "Z");
        transliterationMap.put('И', "I");
        transliterationMap.put('Ј', "J");
        transliterationMap.put('К', "K");
        transliterationMap.put('Л', "L");
        transliterationMap.put('Љ', "Lj");
        transliterationMap.put('М', "M");
        transliterationMap.put('Н', "N");
        transliterationMap.put('Њ', "Nj");
        transliterationMap.put('О', "O");
        transliterationMap.put('П', "P");
        transliterationMap.put('Р', "R");
        transliterationMap.put('С', "S");
        transliterationMap.put('Т', "T");
        transliterationMap.put('Ћ', "C");
        transliterationMap.put('У', "U");
        transliterationMap.put('Ф', "F");
        transliterationMap.put('Х', "H");
        transliterationMap.put('Ц', "C");
        transliterationMap.put('Ч', "C");
        transliterationMap.put('Џ', "Dz");
        transliterationMap.put('Ш', "S");

        // Cyrillic to Latin lowercase
        transliterationMap.put('а', "a");
        transliterationMap.put('б', "b");
        transliterationMap.put('в', "v");
        transliterationMap.put('г', "g");
        transliterationMap.put('д', "d");
        transliterationMap.put('ђ', "dj");
        transliterationMap.put('е', "e");
        transliterationMap.put('ж', "z");
        transliterationMap.put('з', "z");
        transliterationMap.put('и', "i");
        transliterationMap.put('ј', "j");
        transliterationMap.put('к', "k");
        transliterationMap.put('л', "l");
        transliterationMap.put('љ', "lj");
        transliterationMap.put('м', "m");
        transliterationMap.put('н', "n");
        transliterationMap.put('њ', "nj");
        transliterationMap.put('о', "o");
        transliterationMap.put('п', "p");
        transliterationMap.put('р', "r");
        transliterationMap.put('с', "s");
        transliterationMap.put('т', "t");
        transliterationMap.put('ћ', "c");
        transliterationMap.put('у', "u");
        transliterationMap.put('ф', "f");
        transliterationMap.put('х', "h");
        transliterationMap.put('ц', "c");
        transliterationMap.put('ч', "c");
        transliterationMap.put('џ', "dz");
        transliterationMap.put('ш', "s");

        // Greek to Latin uppercase
        transliterationMap.put('Α', "A");
        transliterationMap.put('Β', "B");
        transliterationMap.put('Γ', "G");
        transliterationMap.put('Δ', "D");
        transliterationMap.put('Ε', "E");
        transliterationMap.put('Ζ', "Z");
        transliterationMap.put('Η', "E");
        transliterationMap.put('Θ', "Th");
        transliterationMap.put('Ι', "I");
        transliterationMap.put('Κ', "K");
        transliterationMap.put('Λ', "L");
        transliterationMap.put('Μ', "M");
        transliterationMap.put('Ν', "N");
        transliterationMap.put('Ξ', "X");
        transliterationMap.put('Ο', "O");
        transliterationMap.put('Π', "P");
        transliterationMap.put('Ρ', "R");
        transliterationMap.put('Σ', "S");
        transliterationMap.put('Τ', "T");
        transliterationMap.put('Υ', "Y");
        transliterationMap.put('Φ', "Ph");
        transliterationMap.put('Χ', "Ch");
        transliterationMap.put('Ψ', "Ps");
        transliterationMap.put('Ω', "O");

        // Greek to Latin lowercase
        transliterationMap.put('α', "a");
        transliterationMap.put('β', "b");
        transliterationMap.put('γ', "g");
        transliterationMap.put('δ', "d");
        transliterationMap.put('ε', "e");
        transliterationMap.put('ζ', "z");
        transliterationMap.put('η', "e");
        transliterationMap.put('θ', "th");
        transliterationMap.put('ι', "i");
        transliterationMap.put('κ', "k");
        transliterationMap.put('λ', "l");
        transliterationMap.put('μ', "m");
        transliterationMap.put('ν', "n");
        transliterationMap.put('ξ', "x");
        transliterationMap.put('ο', "o");
        transliterationMap.put('π', "p");
        transliterationMap.put('ρ', "r");
        transliterationMap.put('σ', "s");
        transliterationMap.put('ς', "s");
        transliterationMap.put('τ', "t");
        transliterationMap.put('υ', "y");
        transliterationMap.put('φ', "ph");
        transliterationMap.put('χ', "ch");
        transliterationMap.put('ψ', "ps");
        transliterationMap.put('ω', "o");
    }
}
