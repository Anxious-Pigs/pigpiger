package org.anxiouspigs.filepigger.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class FilepiggerClient {

    public FilepiggerClient(Properties properties) {

    }

    public void upload(InputStream inputStream) throws IOException {
        int num = inputStream.available();
        System.out.println(num);
    }
}
