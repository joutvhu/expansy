# Expansy

Expansy is an expression analyzer that allows you to declare expression elements of your design.

## Installation

- If you are using Gradle just add the following dependency to your `build.gradle`.

```groovy
implementation "com.github.joutvhu:expansy:1.0.0"
```

- Or add the following dependency to your `pom.xml` if you are using Maven.

```xml
<dependency>
    <groupId>com.github.joutvhu</groupId>
    <artifactId>expansy</artifactId>
    <version>1.0.0</version>
</dependency>
```

## How to use?

First you need to define the elements of your design.
The elements need to implement the interface `com.joutvhu.expansy.element.Element`.

```java
public class NumberElement implements Element<BigDecimal> {
    @Override
    public String name() {
        return "Number";
    }

    @Override
    public void define(Definer<BigDecimal> definer) {
        definer
                .name("value")
                .numeric();
    }

    @Override
    public BigDecimal render(Node<BigDecimal> node) {
        String value = node.getString("value");
        if (value == null || value.length() == 0)
            return null;
        return new BigDecimal(value);
    }
}
```

Each element need to implement `define` and `render` methods.
- The `define` method is used to declare the structure of the element. Use the [match functions](#match-functions) below to define the structure.
- The `render` method is used to create the result object based on the analyzed node.
- Also, you can override the `name` method to name the element (_optional_). If you don't overwrite the default is the name of the element class.

## Match functions

#### `size`

Used to match a string of characters of the specified length.

- `size(int length)`
```java
public void define(Definer<Object> definer) {
    definer.size(5);
}
```

#### `character`

Used to match specified characters.

- `character(char... characters)` match one character contained in the `characters` array.
```java
public void define(Definer<Object> definer) {
    definer.character('a', 'b');
}
```

- `characters(char... characters)` match multiple characters contained in the `characters` array.
```java
public void define(Definer<Object> definer) {
    definer.characters('1', '2', '3');
}
```

- `characters(char[] characters, int length)` match `{length}` characters, each of which must be contained in the `characters` array.
```java
public void define(Definer<Object> definer) {
    definer.characters(new char[]{'1', '2', '3'), 10);
}
```

- `characters(char[] characters, Integer minLength, Integer maxLength)` match a minimum of `{minLength}` characters and a maximum of `{maxLength}` characters, each of which must be contained in the `characters` array.
```java
public void define(Definer<Object> definer) {
    definer.characters(new char[]{'1', '2', '3'), 10, 20);
}
```

#### `space`

Used to match space character.

- `space()` match one space character.
```java
public void define(Definer<Object> definer) {
    definer.space();
}
```

- `spaces()` match multiple space characters.
```java
public void define(Definer<Object> definer) {
    definer.spaces();
}
```

- `spaces(int length)` match `{length}` space characters.
```java
public void define(Definer<Object> definer) {
    definer.spaces(10);
}
```

- `spaces(Integer minLength, Integer maxLength)` match a minimum of `{minLength}` space characters and a maximum of `{maxLength}` space characters.
```java
public void define(Definer<Object> definer) {
    definer.spaces(10, 20);
}
```

#### `whitespace`

Used to match any whitespace character (spaces, tabs, line breaks).

- `whitespace()` match one whitespace character.
```java
public void define(Definer<Object> definer) {
    definer.whitespace();
}
```

- `whitespaces()` match multiple whitespace characters.
```java
public void define(Definer<Object> definer) {
    definer.whitespaces();
}
```

- `whitespaces(int length)` match `{length}` whitespace characters.
```java
public void define(Definer<Object> definer) {
    definer.whitespaces(10);
}
```

- `whitespaces(Integer minLength, Integer maxLength)` match a minimum of `{minLength}` whitespace characters and a maximum of `{maxLength}` whitespace characters.
```java
public void define(Definer<Object> definer) {
    definer.whitespaces(10, 20);
}
```

#### `digit`

Used to match any digit character (0-9).

- `digit()` match one digit character.
```java
public void define(Definer<Object> definer) {
    definer.digit();
}
```

- `digits()` match multiple digit characters.
```java
public void define(Definer<Object> definer) {
    definer.digits();
}
```

- `digits(int length)` match `{length}` digit characters.
```java
public void define(Definer<Object> definer) {
    definer.digits(10);
}
```

- `digits(Integer minLength, Integer maxLength)` match a minimum of `{minLength}` digit characters and a maximum of `{maxLength}` digit characters.
```java
public void define(Definer<Object> definer) {
    definer.digits(10, 20);
}
```

#### `lowercase`

Used to match any lowercase character.

- `lowercase()` match one lowercase character.
```java
public void define(Definer<Object> definer) {
    definer.lowercase();
}
```

- `lowercases()` match multiple lowercase characters.
```java
public void define(Definer<Object> definer) {
    definer.lowercases();
}
```

- `lowercases(int length)` match `{length}` lowercase characters.
```java
public void define(Definer<Object> definer) {
    definer.lowercases(10);
}
```

- `lowercases(Integer minLength, Integer maxLength)` match a minimum of `{minLength}` lowercase characters and a maximum of `{maxLength}` lowercase characters.
```java
public void define(Definer<Object> definer) {
    definer.lowercases(10, 20);
}
```

#### `uppercase`

Used to match any uppercase character.

- `uppercase()` match one uppercase character.
```java
public void define(Definer<Object> definer) {
    definer.uppercase();
}
```

- `uppercases()` match multiple uppercase characters.
```java
public void define(Definer<Object> definer) {
    definer.uppercases();
}
```

- `uppercases(int length)` match `{length}` uppercase characters.
```java
public void define(Definer<Object> definer) {
    definer.uppercases(10);
}
```

- `uppercases(Integer minLength, Integer maxLength)` match a minimum of `{minLength}` uppercase characters and a maximum of `{maxLength}` uppercase characters.
```java
public void define(Definer<Object> definer) {
    definer.uppercases(10, 20);
}
```

#### `alphabet`

Used to match any alphabet character.

- `alphabet()` match one alphabet character.
```java
public void define(Definer<Object> definer) {
    definer.alphabet();
}
```

- `alphabets()` match multiple alphabet characters.
```java
public void define(Definer<Object> definer) {
    definer.alphabets();
}
```

- `alphabets(int length)` match `{length}` alphabet characters.
```java
public void define(Definer<Object> definer) {
    definer.alphabets(10);
}
```

- `alphabets(Integer minLength, Integer maxLength)` match a minimum of `{minLength}` alphabet characters and a maximum of `{maxLength}` alphabet characters.
```java
public void define(Definer<Object> definer) {
    definer.alphabets(10, 20);
}
```

#### `alphanumeric`

Used to match any alphanumeric character.

- `alphanumeric()` match one alphanumeric character.
```java
public void define(Definer<Object> definer) {
    definer.alphanumeric();
}
```

- `alphanumerics()` match multiple alphanumeric characters.
```java
public void define(Definer<Object> definer) {
    definer.alphanumerics();
}
```

- `alphanumerics(int length)` match `{length}` alphanumeric characters.
```java
public void define(Definer<Object> definer) {
    definer.alphanumerics(10);
}
```

- `alphanumerics(Integer minLength, Integer maxLength)` match a minimum of `{minLength}` alphanumeric characters and a maximum of `{maxLength}` alphanumeric characters.
```java
public void define(Definer<Object> definer) {
    definer.alphanumerics(10, 20);
}
```

#### `numeric`

Used to match a numeric.

- `numeric()`
```java
public void define(Definer<Object> definer) {
    definer.numeric();
}
```

#### `word`

Used to match a word (any alphanumeric or underscore).

- `word()`
```java
public void define(Definer<Object> definer) {
    definer.word();
}
```

#### `quote`

Used to match a quote.

- `quote()`
```java
public void define(Definer<Object> definer) {
    definer.quote();
}
```

- `quote(char... types)`
```java
public void define(Definer<Object> definer) {
    definer.quote('"');
}
```

#### `equals`

Used to match the specified strings.

- `equals(String value)`
```java
public void define(Definer<Object> definer) {
    definer.equals("abc");
}
```

- `equals(String... values)`
```java
public void define(Definer<Object> definer) {
    definer.equals("abc", "123");
}
```

- `equals(List<String> values)`
```java
public void define(Definer<Object> definer) {
    definer.equals(Arrays.asList("abc", "123"));
}
```

#### `equalsIgnoreCase`

Used to match the specified strings but ignore case.

- `equalsIgnoreCase(String value)`
```java
public void define(Definer<Object> definer) {
    definer.equalsIgnoreCase("abc");
}
```

- `equalsIgnoreCase(String... values)`
```java
public void define(Definer<Object> definer) {
    definer.equalsIgnoreCase("abc", "123");
}
```

- `equalsIgnoreCase(List<String> values)`
```java
public void define(Definer<Object> definer) {
    definer.equalsIgnoreCase(Arrays.asList("abc", "123"));
}
```

#### `pattern`

Used to match a specified pattern.

- `pattern(String regex)`
```java
public void define(Definer<Object> definer) {
    definer.pattern("^-?[0-9]+(\\.[0-9]+)?$");
}
```

- `pattern(String regex, int length)`
```java
public void define(Definer<Object> definer) {
    definer.pattern("^-?[0-9]+(\\.[0-9]+)?$", 16);
}
```

- `pattern(String regex, Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.pattern("^-?[0-9]+(\\.[0-9]+)?$", 12, 32);
}
```

- `pattern(Pattern pattern)`
```java
public void define(Definer<Object> definer) {
    definer.pattern(Pattern.compile("^-?[0-9]+(\\.[0-9]+)?$", 0));
}
```

- `pattern(Pattern pattern, int length)`
```java
public void define(Definer<Object> definer) {
    definer.pattern(Pattern.compile("^-?[0-9]+(\\.[0-9]+)?$", 0), 16);
}
```

- `pattern(Pattern pattern, Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.pattern(Pattern.compile("^-?[0-9]+(\\.[0-9]+)?$", 0), 12, 32);
}
```

#### `match`

Use a matcher to match.

- `match(MatchFunction<E> matcher)`
```java
public void define(Definer<Object> definer) {
    definer.match(new ArrayMatcher());
}
```

#### `check`

Use a method to match.

- `check(Function<String, Boolean> checker)`

- `check(Function<String, Boolean> checker, int length)`

- `check(Function<String, Boolean> checker, Integer minLength, Integer maxLength)`

#### `element`

Used to match the provided elements.

- `element(Element<E> element)`
```java
public void define(Definer<Object> definer) {
    definer.element(new NumberElement());
}
```

- `element(Element<E>... elements)`
```java
public void define(Definer<Object> definer) {
    definer.element(new MultiplyElement(), new DivisionElement());
}
```

- `element(List<Element<E>> elements)`
```java
public void define(Definer<Object> definer) {
    definer.element(Arrays.asList(new MultiplyElement(), new DivisionElement()));
}
```

#### `elements`

Used to match all registered elements.

- `elements()`
```java
public void define(Definer<Object> definer) {
    definer.elements();
}
```

#### `include`

Used to match the listed elements.

- `include(String element)`
```java
public void define(Definer<Object> definer) {
    definer.include("Number");
}
```

- `include(String... elements)`
```java
public void define(Definer<Object> definer) {
    definer.include("Add", "Subtract");
}
```

- `include(List<String> elements)`
```java
public void define(Definer<Object> definer) {
    definer.include(Arrays.asList("Add", "Subtract"));
}
```

#### `exclude`

Used to match registered elements except those listed.

- `exclude(String element)`
```java
public void define(Definer<Object> definer) {
    definer.exclude("Number");
}
```

- `exclude(String... elements)`
```java
public void define(Definer<Object> definer) {
    definer.exclude("Add", "Subtract");
}
```

- `exclude(List<String> elements)`
```java
public void define(Definer<Object> definer) {
    definer.exclude(Arrays.asList("Add", "Subtract"));
}
```

#### `maybe`

Used to define a matcher group that may or may not appear.

Syntax:
```
definer
    .maybe()
    ... // content may or may not be appear
    .end()
```


#### `or`

Could match with a group in the matcher groups.

Syntax:
```
definer
    .or()
    ... // case 1
    .or()
    ... // case 2
    .or()
    ... // case 3
    .end()
```

#### `loop`

Used to define a matcher group it is repeatable.

Syntax:
```
definer
    .loop()
    ... // repeatable content
    .end()
```


```java
public class VariableElement implements Element<Object> {
    @Override
    public void define(Definer<Object> definer) {
        definer
            .equals("$")
            .name("name")
            .word()
            .loop()
                .or()
                    .character('.')
                    .name("child")
                    .word()
                .or()
                    .equals("[")
                    .name("child")
                    .quote('\'')
                    .equals("]")
                .end()
            .end();
    }
}
```

#### `between`

Used to define a matcher group it can repeat and be interspersed by another matcher group.

Syntax:
```
definer
    .between()
    ... // content to separate
    .is()
    ... // define separator here
    .end()
```

The following example uses `between` to define function parameters separated by commas.
```java
public class FunctionElement implements Element<BigDecimal> {
    @Override
    public void define(Definer<BigDecimal> definer) {
        definer
            .equals("#")
            .name("name")
            .word()        // Function name
            .equals("(")
            .between()
                .spaces()
                .name("param")
                .elements()    // The parameter can be any element
                .spaces()
            .is()
                .equals(",")   // There is a comma between the parameters
            .end()
            .equals(")");
    }
}
```

## Render