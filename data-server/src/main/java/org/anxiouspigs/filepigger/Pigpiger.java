package org.anxiouspigs.filepigger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Pigpiger {

    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream(new File(args[0])));
        System.out.println(properties.get("data.dir"));
    }
}
