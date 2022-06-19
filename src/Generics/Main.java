package Generics;

class JavaGenerics<T> {

    T varX;

    public JavaGenerics(T varX) {
        this.varX = varX;
    }

    public void printValues() {
        System.out.println("Value : " + varX);
    }
}

public class Main {

    public static void main(String[] args) {
        JavaGenerics<Integer> genObj1 = new JavaGenerics<>(34);
        genObj1.printValues();
        JavaGenerics<Double> genObj2 = new JavaGenerics<>(33.43223423);
        genObj2.printValues();
        JavaGenerics<String> genObj3 = new JavaGenerics<>("Generics!");
        genObj3.printValues();
        genericMethod("Hello generic method!");
    }

    public static  <T> void genericMethod(T varY) {
        System.out.println("Value : " + varY);
    }
}
