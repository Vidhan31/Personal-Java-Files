/*

A simple skeleton pattern is good but may not be helpful where threads are involved.
synchronized is helpful here as it allows one thread at a time to access getInstance method.
However it may worsen performance in case of multiple operations.
The good way is to use double check lock and lazy instantiation.


*/

package SingletonPattern;

public class Main {

    public static void main(String[] args) {

        // Singleton singleton = Singleton.getInstance(); //Singleton lazy instantiation


       Thread t1 = new Thread(new Runnable() { //Double-check Lock
            @Override
            public void run() {
                Singleton singleton = Singleton.getInstance();
                System.out.println(singleton.toString());

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                Singleton singleton = Singleton.getInstance();
                System.out.println(singleton.toString());

            }
        });

        t1.start();
        t2.start();


        /*Thread t1 = new Thread(new Runnable() { //ENUM -> best way for thread-safe.
            @Override
            public void run() {
                SingletonENUM singletonENUM = SingletonENUM.INSTANCE;
                System.out.println(singletonENUM.hashCode());
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                SingletonENUM singletonENUM2 = SingletonENUM.INSTANCE;
                System.out.println(singletonENUM2.hashCode());
            }
        });

        t1.start();
        t2.start();*/

    }
}
