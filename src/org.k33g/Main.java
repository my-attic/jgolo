package org.k33g;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public class Main {

    public static void main(String[] args) throws IOException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String path = null;
        String script = null;
        String method = "main";

        if(args.length == 0 ) {

            path = (new File("")).getAbsolutePath();
            script = "/scripts/main.golo";

        } else {
            path = (new File("")).getAbsolutePath();
            script = args[0];
            if(args.length>1){method = args[1];};
        }

        System.out.println("path "+path);
        System.out.println("script "+script);

        JGoloKlassLoader k = new JGoloKlassLoader(path);
        k.loadAll();
        Object params = null;
        k.module(script).method(method, Object.class).run("");

        /*

        System.out.println(
                k.module("/scripts/libs/core.golo").method("hello", Object.class).run(params)
        );

        System.out.println(
                k.module("/scripts/libs/extensions.golo").method("hello", Object.class).run(params)
        );

        System.out.println(
                k.module("/scripts/libs/core.golo").method("add", Object.class, Object.class).run(4,5)
        );

        */


    }
}
