package OOPS;

interface Animal {
    public void makeNoise();
}

class BaseClass {

    public final int CONST = 3;
    public static int VAR;

    public void printAnonymous() {
        System.out.println("Hello from Base Class!");
    }
}

public class AnonymousInnerClass {

    public static void main(String[] args) {

        BaseClass parentObj = new BaseClass(); //Parent Class
        parentObj.printAnonymous();
        new BaseClass().printAnonymous(); //anonymous object
        BaseClass anonymousInnerObj = new BaseClass() { //Anonymous Inner class and object for one time usage

            public void printAnonymous() {
                System.out.println("Hello from Anonymous Inner Class!");
            }
        };
        anonymousInnerObj.printAnonymous();

        Runnable runnableObj = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello from Runnable interface");
            }
        };
        runnableObj.run();

        Animal animalObj = new Animal() { //interface
            @Override
            public void makeNoise() {
                System.out.println("Animal makes noises");
            }
        };
        animalObj.makeNoise();
    }
}