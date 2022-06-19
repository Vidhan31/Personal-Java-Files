/*
Conventions :
    T – Type
    E – Element
    K – Key
    N – Number
    V – Value
 */

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
        //argument passed to the type parameter must be a reference type. primitive arrays are allowed
        // as they themselves are reference types.
        JavaGenerics<Integer> genObj1 = new JavaGenerics<>(34);
        genObj1.printValues();
        //Type safety: Specifying the type beforehand helps us locate the problem at compile time rather than runtime.
        JavaGenerics<Double> genObj2 = new JavaGenerics<>(33.43223423);
        genObj2.printValues();
        //elements individual type casting
        JavaGenerics<String> genObj3 = new JavaGenerics<>("Generics!");
        genObj3.printValues();
        genericMethod("Hello generic method!");
        //Even if all above are of Type T, that doesn't mean genObj1 = genObj2 = genObj3.
        //They are still different types of T. They are different references.
    }

    public static <T> void genericMethod(T varY) {
        System.out.println("Value : " + varY);
    }
}
