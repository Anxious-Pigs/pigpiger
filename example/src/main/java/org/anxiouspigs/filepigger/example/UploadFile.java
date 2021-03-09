package org.anxiouspigs.filepigger.example;

import org.anxiouspigs.filepigger.client.FilepiggerClient;
import org.anxiouspigs.filepigger.network.FilepiggerSelector;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.Selector;
import java.util.Properties;

public class UploadFile {

    public static void main(String[] args) throws IOException {
        FilepiggerSelector selector = new FilepiggerSelector(Selector.open());
        selector.connect(new InetSocketAddress("localhost", 9000));
        while (true){
            selector.pool();
        }

//        Properties properties = new Properties();
//        properties.setProperty("server", "localhost:9000");
//        FileInputStream fileInputStream = new FileInputStream(new File("config/server.properties"));
//
//        FilepiggerClient filepiggerClient = new FilepiggerClient(properties);
//
//        filepiggerClient.upload(fileInputStream);


    }
}
