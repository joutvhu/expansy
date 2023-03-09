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

- `size` matches a string of characters of the specified length.
- `character` matches specified characters.
- `space` matches space character.
- `whitespace` matches any whitespace character (spaces, tabs, line breaks)
- `digit` matches any digit character (0-9).
- `lowercase` matches any lowercase character.
- `uppercase` matches any uppercase character.
- `alphabet` matches any alphabet character.
- `alphanumeric` matches any alphanumeric character.
- `numeric` matches a numeric.
- `word` matches a word (any alphanumeric or underscore).
- `quote` matches a quote.
- `equals` matches the specified strings.
- `equalsIgnoreCase` matches the specified strings but ignore case.
- `pattern` matches a specified pattern.
- `check` use a method to match.
- `element` matches the provided elements.
- `elements` matches all registered elements.
- `include` matches the listed elements.
- `exclude` matches registered elements except those listed.
- `maybe` to define a matcher group that may or may not appear.
- `or` could match with a matcher group in the matcher groups.
- `loop` to define a matcher group it is repeatable.
- `between` to define a matcher group it can repeat and be interspersed by another matcher group.

## Render