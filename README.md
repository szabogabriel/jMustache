# jMustache

This software is a simple and fairly small implementation
of the Mustach templating engine in pure Java.

The project has no 3rd party dependencies.

The implementation is not complete; the 'Set Delimiter' option
is not supported at the moment.

The data provided to the template is an object implementing
the Map interface. For boolean values, the Boolean wrapper
class should be used. For the lists an object implementing
the List interface should be used, which should contain
objects implementing once again the Map interface.

## Performance

It renders the timeline.mustache (which is an example taken from https://github.com/spullara/mustache.java) around 145.000 times (which is about 29x faster, than the other implementation).
