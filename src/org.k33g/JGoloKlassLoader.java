package org.k33g;


import fr.insalyon.citi.golo.compiler.GoloClassLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

public class JGoloKlassLoader {

    public HashMap<String,Class<?>> modules;

    private String pathToParse = null;
    private String extension = ".golo";
    private GoloClassLoader classLoader = new GoloClassLoader();

    private void findGoloScripts(String path) throws IOException {

        //System.out.println(path+" "+ extension +" "+this.pathToParse );

        File root = new File( path );

        File[] list = root.listFiles(); //filter how to ?

        for ( File f : list ) {
            if ( f.isDirectory() ) {
                findGoloScripts(f.getAbsolutePath());
                //System.out.println("Dir:" + f.getAbsoluteFile());
            }
            else {
                if(f.getAbsoluteFile().getName().endsWith(this.extension)) {
                    //System.out.println("KEY : "+f.getAbsoluteFile().toString().split(this.pathToParse)[1]);
                    System.out.println( "Loading : " + f.getAbsoluteFile() );
                    /*
                    System.out.println( "File:" + f.getAbsoluteFile() );
                    System.out.println( "---- File:" + f.getName() );
                    System.out.println( "---- File:" + f.getAbsoluteFile().toString().split(this.pathToParse)[1] );
                    */
                    Class<?> module = classLoader.load(
                            f.getName(),
                            new FileInputStream(f.getAbsoluteFile())
                    );

                    this.modules.put(f.getAbsoluteFile().toString().replaceAll("\\\\", "/").split(this.pathToParse.replaceAll("\\\\", "/"))[1], module);

                    //this.modules.put(f.getAbsoluteFile().toString().split(this.pathToParse)[1], module);
                }
            }
        }
    }


    public Klass module(String modulePathName) {
        return new Klass(this.modules.get(modulePathName));
    }

    //k.modules.get("/scripts/main.golo").getMethod("main", Object.class).invoke(null, params);


    public void loadAll() {
        try {
            this.findGoloScripts(this.pathToParse);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }


    public JGoloKlassLoader(String pathToParse) {
        this.modules = new HashMap<String, Class<?>>();
        this.pathToParse = pathToParse;

    }
}
