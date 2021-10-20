package com.design;

// Singleton Class with Lazy Instantiation
public class LazyAnnouncer extends Announcer {
    private static LazyAnnouncer instance = null;

    private LazyAnnouncer(String name) {
        setType("LazyAnnouncer");
        setName(name);
    }

    public static LazyAnnouncer getInstance() {
        if(instance == null)
            instance = new LazyAnnouncer(Util.announcerName);
        return instance;
    }
}
