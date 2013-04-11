#JGolo Klass Loader

JGolo is a class loader for [Golo](http://golo-lang.org/). It discovers all golo scripts of your project.

**Pay attention** : JGolo embeds [Golo](http://golo-lang.org/). if you want the last version of Golo, you only have to recompile JGolo.

##Build it

    mvn compile assembly:single

##Use it as command

    cd scripts
    java -jar jgolo.jar /main.golo  #run main() function of main.golo

or :

    cd scripts
    java -jar jgolo.jar /main.golo index  #run index() function of main.golo

you can call function of an other golo script

    cd scripts
    java -jar jgolo.jar /libs/extensions.golo morgen  #run morgen() function of libs/extensions.golo

>>**sorry no arguments**


##Use it as library

```java
JGoloKlassLoader k = new JGoloKlassLoader((new File("")).getAbsolutePath());
k.loadAll();
Object params = null;

k.module("/scripts/main.golo").method("main", Object.class).run("");

System.out.println(
        k.module("/scripts/libs/core.golo").method("hello", Object.class).run(params)
);

System.out.println(
        k.module("/scripts/libs/extensions.golo").method("hello", Object.class).run(params)
);

System.out.println(
        k.module("/scripts/libs/core.golo").method("add", Object.class, Object.class).run(4,5)
);
```