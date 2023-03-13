package com.joutvhu.expansy.match.matcher;

import com.joutvhu.expansy.exception.DefineException;
import com.joutvhu.expansy.match.Definer;
import com.joutvhu.expansy.match.Matcher;
import com.joutvhu.expansy.match.consumer.Consumer;
import com.joutvhu.expansy.match.consumer.StopPoint;
import com.joutvhu.expansy.util.ArrayUtils;
import com.joutvhu.expansy.util.Joiner;

@SuppressWarnings("java:S3776")
public class CharacterMatcher<E> extends Matcher<E> {
    public static final char[] WHITESPACE = new char[]{
            ' ', '\t', '\n'
    };
    public static final char[] DIGIT = new char[]{
            '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    public static final char[] LOWERCASE = new char[]{
            'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'
    };
    public static final char[] UPPERCASE = new char[]{
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'
    };
    public static final char[] ALPHABET = ArrayUtils.addAll(LOWERCASE, UPPERCASE);
    public static final char[] ALPHANUMERIC = ArrayUtils.addAll(ALPHABET, DIGIT);

    private final char[] characters;
    private final Integer minLength;
    private final Integer maxLength;

    public CharacterMatcher(Definer<E> parent, char character, Integer length) {
        this(parent, new char[]{character}, length, length);
    }

    public CharacterMatcher(Definer<E> parent, char character, Integer minLength, Integer maxLength) {
        this(parent, new char[]{character}, minLength, maxLength);
    }

    public CharacterMatcher(Definer<E> parent, char[] characters, Integer length) {
        this(parent, characters, length, length);
    }

    public CharacterMatcher(Definer<E> parent, char[] characters, Integer minLength, Integer maxLength) {
        super(parent);
        if (characters == null || characters.length == 0)
            throw new DefineException("The characters must be non-blank.");
        if (minLength != null && minLength < 0)
            throw new DefineException("The minLength cannot be less than 0.");
        if (maxLength != null && maxLength < 1)
            throw new DefineException("The maxLength cannot be less than 1.");
        this.characters = characters;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    @Override
    @SuppressWarnings("java:S2259")
    public void match(Consumer<E> consumer) {
        StopPoint point;
        if (minLength == null || minLength == 0)
            consumer.push();
        if (minLength != null && minLength > 0) {
            point = consumer.next(minLength);
            if (point == null)
                consumer.error("Minimum length is {0}", minLength);
            for (char c : point.getValue().toCharArray()) {
                if (!contains(c))
                    consumer.error("The character {0} is not one of the following characters {1}", c, Joiner.on(",").join(characters));
            }
            consumer.push();
            if (maxLength != null && maxLength <= point.getLength())
                consumer.close();
        }

        do {
            point = consumer.next();
            if (point == null)
                consumer.error("No characters.");
            if (!contains(point.getCharacter()))
                consumer.error("The character {0} is not one of the following characters {1}", point.getCharacter(), Joiner.on(",").join(characters));
            consumer.push();
        } while (maxLength == null || point.getLength() < maxLength);
        consumer.close();
    }

    private boolean contains(char character) {
        for (char c : characters) {
            if (c == character)
                return true;
        }
        return false;
    }
}
