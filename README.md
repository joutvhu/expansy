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
The elements need to be implemented with two methods `define` and `render`.

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

- The `define` method is used to declare the structure of the element. Use the match functions below to define the structure.
- The `render` method is used to create the result object based on the analyzed node.
- Also, you can override the `name` method to name the element (_optional_). If you don't overwrite the default is the name of the element class.

## Match functions

- `size`
- `character`
- `space`
- `whitespace`
- `digit`
- `lowercase`
- `uppercase`
- `alphabet`
- `alphanumeric`
- `numeric`
- `word`
- `quote`
- `equals`
- `equalsIgnoreCase`
- `pattern`
- `check`
- `element`
- `elements`
- `include`
- `exclude`
- `maybe`
- `or`
- `loop`
- `between`

## Render