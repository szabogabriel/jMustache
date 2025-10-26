# jMustache

This software is a simple and fairly small implementation of the Mustache templating engine in pure Java.

The project has no 3rd party dependencies.

The data provided to the template is an object implementing the Map interface. For boolean values, the Boolean wrapper class should be used. For the lists an object implementing the List interface should be used, which should contain objects implementing once again the Map interface.

## Installation

### Using Maven

Add the following to your `pom.xml`:

```xml
<dependency>
    <groupId>com.github.szabogabriel</groupId>
    <artifactId>jMustache</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

You'll also need to add the GitHub Packages repository:

```xml
<repositories>
    <repository>
        <id>github</id>
        <url>https://maven.pkg.github.com/szabogabriel/jMustache</url>
    </repository>
</repositories>
```

### Authentication

To use GitHub Packages, you need to authenticate. Create or edit your `~/.m2/settings.xml`:

```xml
<settings>
    <servers>
        <server>
            <id>github</id>
            <username>YOUR_GITHUB_USERNAME</username>
            <password>YOUR_GITHUB_TOKEN</password>
        </server>
    </servers>
</settings>
```

Replace `YOUR_GITHUB_USERNAME` with your GitHub username and `YOUR_GITHUB_TOKEN` with a [GitHub Personal Access Token](https://github.com/settings/tokens) with `read:packages` permission.

## Functionality

The implementation is not complete; the 'Set Delimiter' option is not supported at the moment.

Additionally, embedded keys in dynamic values are supported. E.g. `{{key.{{discriminator}}}}` is a dynamic value, where `discriminator` can be set independently. So a dynamic resolution of final value is possible.

## Performance

It renders the timeline.mustache (which is an example taken from https://github.com/spullara/mustache.java) around 145.000 times per second (which is about 29x faster, than the other implementation).
