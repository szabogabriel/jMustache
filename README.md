# jMustache

This software is a simple and fairly small implementation of the Mustache templating engine in pure Java.

The project has no 3rd party dependencies.

The data provided to the template is an object implementing the Map interface. For boolean values, the Boolean wrapper class should be used. For the lists an object implementing the List interface should be used, which should contain objects implementing once again the Map interface.

## Installation

### Using Maven (via JitPack)

Add the JitPack repository to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

Then add the dependency:

```xml
<dependency>
    <groupId>com.github.szabogabriel</groupId>
    <artifactId>jMustache</artifactId>
    <version>v0.1.0</version>
</dependency>
```

**Note:** Replace `v0.1.0` with the latest release version from [Releases](https://github.com/szabogabriel/jMustache/releases).

### Using Gradle

```gradle
repositories {
    maven { url 'https://jitpack.io' }
}

dependencies {
    implementation 'com.github.szabogabriel:jMustache:v0.1.0'
}
```

### Download JAR Directly

You can also download the JAR file directly from the [Releases page](https://github.com/szabogabriel/jMustache/releases).

## Functionality

The implementation is not complete; the 'Set Delimiter' option is not supported at the moment.

Additionally, embedded keys in dynamic values are supported. E.g. `{{key.{{discriminator}}}}` is a dynamic value, where `discriminator` can be set independently. So a dynamic resolution of final value is possible.

## Performance

It renders the timeline.mustache (which is an example taken from https://github.com/spullara/mustache.java) around 145.000 times per second (which is about 29x faster, than the other implementation).
