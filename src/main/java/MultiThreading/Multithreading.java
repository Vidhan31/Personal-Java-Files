package MultiThreading;/*

At a time one thread is executed only. Which thread will be executed next is always random.
Thread setPriority() method is just a hint to OS task scheduler and is dependent on the underlying OS.
OS will try to allocate more resources to a high priority thread but it does not guarantee it.

New State : When a new thread is created. It is not running.

Active state : When the thread invoked start(), its moved to active state where it can either be runnable or running.

Runnable: Ready to be executed at given instant of time. May be executing already too.

Running : The thread is currently running. The thread switches from runnable to running and vice versa commonly.

Blocked/Waiting : Suspended state. Doesn't consume CPU cycle.

Timed Waiting : Suspended state so that thread doesn't run forever. Eg: by sleep(2000) method. After 2 seconds, thread
                will wake up and start executing from where it finished.

Termination : When thread terminates normally after finishing its work or abnormally due to unhandled exception, etc.

If you need to have subclasses use Runnable interface as Java doesn't support multiple inheritance. Also, you can
implement many interfaces.

when we use start(), it invokes a new thread where run() method runs. run() is a normal helper method only.

Java Thread Scheduler works by combining different methods/algorithms such as FIFO, time slicing and pre-emptive
priority scheduling. If two threads have same priority and time arrival, then FIFO is applied otherwise pre-emptive and
both with time slicing.

If we call thread1.join and thread2.join, then Thread 1 and thread 2 will continue running in parallel.
It's just that the main method or any other method where join is called needs them both to finish before it can continue

synchronized prevents multiple threads from preventing accessing data at the same time to avoid errors, inconsistencies.
It acquires lock of the object and releases it once it finishes its task with object. As opposed to join(), synchronized
prevents any other thread to act until the current thread finishes its job or returns value.

For object, object level lock is acquired. For static, it acquires class level lock as static members are of class.

currentThread returns the thread of the method which currentThread() is associated with.

Daemon Threads are child of normal thread and dies when normal thread dies. Garbage collection is performed by a daemon
thread called Garbage Collector(GC). It is a background service with low priority. JVM exists cuz of it.

yield() makes the current thread and give other threads chance to execute.

wait() belongs to Object class so it can work on any object. It makes current thread wait till notified.
notify() notifies the another thread with wait() when it finishes its work. synchronized keyword is used for wait().



*/

class BaseThread implements Runnable {
    protected int temp = Thread.MAX_PRIORITY;

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hello from Base Thread: " + Thread.currentThread().getPriority());
            try {
                Thread.sleep(1500);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class SubThread extends BaseThread {
    protected int temp;

    public SubThread() {
        this.temp = super.temp;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Hello from SubThread " + Thread.currentThread().getPriority());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

class Classy {
    public int count = 0;

    public synchronized void increment() {
        count++;
    }
}

public class Multithreading {

    public static void main(String[] args) {
        //passing new object directly to the constructor works fine and is good if you don't have any use of that
        //object in the future. If you have use in future, store object in reference variable.

        Thread task = new Thread(new BaseThread());
        Thread task2 = new Thread(new SubThread());

        System.out.println("Main : " + Thread.currentThread().getPriority());
        task.setPriority(Thread.MIN_PRIORITY);
        task2.setPriority(Thread.MAX_PRIORITY);

        task.setName("Base Thread");
        task.start();
        task2.setName("Sub Thread");
        task2.start();

        try {
            task.join(); //It asks Main method thread to wait for task to finish the job.
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


        Runtime r = Runtime.getRuntime();
        System.out.println("Total Memory: " + r.totalMemory());
        System.out.println("Free Memory: " + r.freeMemory());
        for (int i = 0; i < 10000; i++) {
            new Multithreading();
        }
        System.out.println("After creating 10000 instance, Free Memory: " + r.freeMemory());
        System.gc();
        System.out.println("After gc(), Free Memory: " + r.freeMemory());
        System.out.println("Number of processes: " + Runtime.getRuntime().availableProcessors());


        //Creating 4 threads where each executes 500 times to make count 2000 instead of
        //one thread doing all the work.
        Classy c = new Classy();

        Thread task3 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                c.increment();
            }
        });

        Thread task4 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                c.increment();
            }
        });

        Thread task5 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 500; i++) {
                    c.increment();
                }
            }
        });

        Thread task6 = new Thread(() -> {
            for (int i = 0; i < 500; i++) {
                c.increment();
            }
        });

        task3.setName("Anonymous Inner class 1 thread");
        task4.setName("Anonymous Inner class 2 thread");
        task5.setName("Anonymous Inner class 3 thread");
        task6.setName("Anonymous Inner class 4 thread");
        task3.start();
        task4.start();
        task5.start();
        task6.start();

        try {
            task3.join();
            task4.join();
            task5.join();
            task6.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Count : " + c.count);

        /* Multithreading using lambda

        Thread t7 = new Thread(new Runnable() {
            @Override
            public void run() {
               ...
            }
        });

        We are creating anonymous class where we pass Runnable interface as argument but run() is executing very
        simple logic so we use lambda to reduce line of codes. Instead of new runnable, we can directly pass logic of
        run() (which is very small and not complex) as an argument using lambda thereby reducing the boilerplate code.
        We can do so because lambda is functional interface. */

        Thread t7 = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Lambda thread");
        });
        t7.start();
        try {
            t7.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Program terminates");
    }
}
