package SingletonPattern;

public class Singleton { //lazy instantiation

    private Singleton() {

        System.out.println("Instance created.");
    }

    private static Singleton singleton = null;

    public static Singleton getInstance() {

        if (singleton == null) {
            singleton = new Singleton();
        }
        return singleton;
    }
}
