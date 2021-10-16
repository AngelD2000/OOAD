package com.design;

// Singleton Class with Eager Instantiation
public class EagerAnnouncer extends Announcer {
    private static final EagerAnnouncer instance = new EagerAnnouncer(Util.announcerName);

    private EagerAnnouncer(String name) {
        setType("EagerAnnouncer");
        setName(name);
    }

    public static EagerAnnouncer getInstance() {
        return instance;
    }
}
