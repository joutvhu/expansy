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

#### `character`

Used to match specified characters.

#### `space`

Used to match space character.

#### `whitespace`

Used to match any whitespace character (spaces, tabs, line breaks).

#### `digit`

Used to match any digit character (0-9).

#### `lowercase`

Used to match any lowercase character.

#### `uppercase`

Used to match any uppercase character.

#### `alphabet`

Used to match any alphabet character.

#### `alphanumeric`

Used to match any alphanumeric character.

#### `numeric`

Used to match a numeric.

#### `word`

Used to match a word (any alphanumeric or underscore).

#### `quote`

Used to match a quote.

#### `equals`

Used to match the specified strings.

#### `equalsIgnoreCase`

Used to match the specified strings but ignore case.

#### `pattern`

Used to match a specified pattern.

#### `check`

Use a method to match.

#### `element`

Used to match the provided elements.

#### `elements`

Used to match all registered elements.

#### `include`

Used to match the listed elements.

#### `exclude`

Used to match registered elements except those listed.

#### `maybe`

Used to define a matcher group that may or may not appear.

#### `or`

Could match with a group in the matcher groups.

#### `loop`

Used to define a matcher group it is repeatable.

#### `between`

Used to define a matcher group it can repeat and be interspersed by another matcher group.

## Render