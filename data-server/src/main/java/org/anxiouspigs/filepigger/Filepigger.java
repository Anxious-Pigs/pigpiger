package org.anxiouspigs.filepigger;

import org.anxiouspigs.filepigger.network.FilepiggerSelector;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.util.Properties;

public class Filepigger {

    public static void main(String[] args) throws IOException {
//        Properties properties = new Properties();
//        properties.load(new FileInputStream(new File(args[0])));
//        System.out.println(properties.get("data.dir"));


        FilepiggerSelector selector = new FilepiggerSelector(Selector.open());
        selector.bind(new InetSocketAddress(9000));
        while (true) {
            selector.pool();
        }
    }
}
