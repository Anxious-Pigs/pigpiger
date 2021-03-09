package org.anxiouspigs.filepigger.api;

public enum ApiNames {

    HEARTBEAT(0, "Heartbeat");

    public final short id;
    public final String name;

    ApiNames(int id, String name) {
        this.id = (short) id;
        this.name = name;
    }



}
