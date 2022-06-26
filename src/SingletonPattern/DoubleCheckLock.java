package SingletonPattern;

public class DoubleCheckLock { //lazy instantiation -> Double check lock part 1

    private DoubleCheckLock() {

        System.out.println("Instance created.");
    }

    //volatile tells jvm not to change order of code instructions
    private volatile static DoubleCheckLock checkLock = null;

    //https://youtu.be/Z5TRputhzHs?t=258
    public static DoubleCheckLock getInstance() {

        // outer if block stops the wastage of time for threads t3 & above as they don't have to wait at the
        // synchronized block.
        if (checkLock == null) {

            synchronized (Singleton.class) { // Singleton.class is used instead of "this" for static context.
                if (checkLock == null) {
                    checkLock = new DoubleCheckLock();
                }
            }
        }
        return checkLock;
    }
}
