package com.joutvhu.dynamic.expression.analysis.match.func;

import com.joutvhu.dynamic.expression.analysis.match.MatchFunction;
import com.joutvhu.dynamic.expression.analysis.match.Matcher;

public class CharacterMatcher<E> extends MatchFunction<E> {
    public static char[] WHITESPACE = new char[]{
            ' ', '\t', '\n'
    };
    public static char[] DIGIT = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    public static char[] LOWERCASE = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    public static char[] UPPERCASE = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static char[] ALPHABET = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };

    private char[] characters;
    private Integer time;

    public CharacterMatcher(Matcher<E> parent, char character, Integer time) {
        super(parent);
        this.characters = new char[]{character};
        this.time = time;
    }

    public CharacterMatcher(Matcher<E> parent, char[] characters, Integer time) {
        super(parent);
        this.characters = characters;
        this.time = time;
    }
}
