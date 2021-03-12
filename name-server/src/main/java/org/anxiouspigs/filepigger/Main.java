package org.anxiouspigs.filepigger;

public class Main {
    public static void main(String[] args) {
        String dir = "/image/red/red.jpg";
        System.out.println(dir.substring(dir.lastIndexOf("/") + 1));
    }
}
