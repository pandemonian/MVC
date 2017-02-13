package model;

/**
 * Created by Gubanov Pavel on 06.01.17.
 */
enum Singleton {
    INSTANCE;

    private final Initializer init;

    Singleton() {
        init = new Initializer();
    }

    public Initializer getInit() {
        return init;
    }
}
