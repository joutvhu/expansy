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

- `character(char... characters)` 
```java
public void define(Definer<Object> definer) {
    definer.character('a', 'b');
}
```

- `characters(char... characters)`
```java
public void define(Definer<Object> definer) {
    definer.characters('1', '2', '3');
}
```

- `characters(char[] characters, int length)`
```java
public void define(Definer<Object> definer) {
    definer.characters(new char[]{'1', '2', '3'), 10);
}
```

- `characters(char[] characters, Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.characters(new char[]{'1', '2', '3'), 10, 20);
}
```

#### `space`

Used to match space character.

- `space()`
```java
public void define(Definer<Object> definer) {
    definer.space();
}
```

- `spaces()`
```java
public void define(Definer<Object> definer) {
    definer.spaces();
}
```

- `spaces(int length)`
```java
public void define(Definer<Object> definer) {
    definer.spaces(10);
}
```

- `spaces(Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.spaces(10, 20);
}
```

#### `whitespace`

Used to match any whitespace character (spaces, tabs, line breaks).

- `whitespace()`
```java
public void define(Definer<Object> definer) {
    definer.whitespace();
}
```

- `whitespaces()`
```java
public void define(Definer<Object> definer) {
    definer.whitespaces();
}
```

- `whitespaces(int length)`
```java
public void define(Definer<Object> definer) {
    definer.whitespaces(10);
}
```

- `whitespaces(Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.whitespaces(10, 20);
}
```

#### `digit`

Used to match any digit character (0-9).

- `digit()`
```java
public void define(Definer<Object> definer) {
    definer.digit();
}
```

- `digits()`
```java
public void define(Definer<Object> definer) {
    definer.digits();
}
```

- `digits(int length)`
```java
public void define(Definer<Object> definer) {
    definer.digits(10);
}
```

- `digits(Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.digits(10, 20);
}
```

#### `lowercase`

Used to match any lowercase character.

- `lowercase()`
```java
public void define(Definer<Object> definer) {
    definer.lowercase();
}
```

- `lowercases()`
```java
public void define(Definer<Object> definer) {
    definer.lowercases();
}
```

- `lowercases(int length)`
```java
public void define(Definer<Object> definer) {
    definer.lowercases(10);
}
```

- `lowercases(Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.lowercases(10, 20);
}
```

#### `uppercase`

Used to match any uppercase character.

- `uppercase()`
```java
public void define(Definer<Object> definer) {
    definer.uppercase();
}
```

- `uppercases()`
```java
public void define(Definer<Object> definer) {
    definer.uppercases();
}
```

- `uppercases(int length)`
```java
public void define(Definer<Object> definer) {
    definer.uppercases(10);
}
```

- `uppercases(Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.uppercases(10, 20);
}
```

#### `alphabet`

Used to match any alphabet character.

- `alphabet()`
```java
public void define(Definer<Object> definer) {
    definer.alphabet();
}
```

- `alphabets()`
```java
public void define(Definer<Object> definer) {
    definer.alphabets();
}
```

- `alphabets(int length)`
```java
public void define(Definer<Object> definer) {
    definer.alphabets(10);
}
```

- `alphabets(Integer minLength, Integer maxLength)`
```java
public void define(Definer<Object> definer) {
    definer.alphabets(10, 20);
}
```

#### `alphanumeric`

Used to match any alphanumeric character.

- `alphanumeric()`
```java
public void define(Definer<Object> definer) {
    definer.alphanumeric();
}
```

- `alphanumerics()`
```java
public void define(Definer<Object> definer) {
    definer.alphanumerics();
}
```

- `alphanumerics(int length)`
```java
public void define(Definer<Object> definer) {
    definer.alphanumerics(10);
}
```

- `alphanumerics(Integer minLength, Integer maxLength)`
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

- `pattern(String regex, int length)`

- `pattern(String regex, Integer minLength, Integer maxLength)`

- `pattern(Pattern pattern)`

- `pattern(Pattern pattern, int length)`

- `pattern(Pattern pattern, Integer minLength, Integer maxLength)`

#### `check`

Use a method to match.

- `check(MatchFunction<E> checker)`

- `check(Function<String, Boolean> checker)`

- `check(Function<String, Boolean> checker, int length)`

- `check(Function<String, Boolean> checker, Integer minLength, Integer maxLength)`

#### `element`

Used to match the provided elements.

- `element(Element<E> element)`

- `element(Element<E>... elements)`

- `element(List<Element<E>> elements)`

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
    ...
    .end()
```


#### `or`

Could match with a group in the matcher groups.

Syntax:
```
definer
    .or()
    ...
    .or()
    ...
    .or()
    ...
    .end()
```

#### `loop`

Used to define a matcher group it is repeatable.

Syntax:
```
definer
    .loop()
    ...
    .end()
```

#### `between`

Used to define a matcher group it can repeat and be interspersed by another matcher group.

Syntax:
```
definer
    .between()
    ... // content to separate.
    .is()
    ... // define separator here.
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