/*
Conventions :
    T – Type
    E – Element
    K – Key
    N – Number
    V – Value
 */

package Generics;

class JavaGenerics<T, U> {

    T varX;
    U varY;

    public JavaGenerics(T varX, U varY) {
        this.varX = varX;
        this.varY = varY;
    }

    public void printValues() {
        System.out.println("Value X: " + varX);
        System.out.println("Value Y: " + varY);
    }
}

class SpecializedGenericTypes<T extends Animal> {
    //class SpecializedGenericTypes<T extends Dog> -> gives error if you pass anything other than Dog even animal.

    // class SpecializedGenericTypes<T extends Animal & Serializable>
    //In case you implementing interfaces, use & between. order should be interface after class.
    T species;

    public SpecializedGenericTypes(T species) {
        this.species = species;
    }

    public void printObject() {
        species.makeNoise();
    }

    public T returnObject() { // return type T object
        return species;
    }

}

public class Main {

    public static void main(String[] args) {

        //argument passed to the type parameter must be a reference type. primitive arrays are allowed
        // as they themselves are reference types.
        JavaGenerics<Integer, String> genObj1 = new JavaGenerics<>(34, "String");
        genObj1.printValues();

        //Type safety: Specifying the type beforehand helps us locate the problem at compile time rather than runtime.
        //here <T> or <Double> is a bound.
        JavaGenerics<Double, Integer> genObj2 = new JavaGenerics<>(33.43223423, 123);
        genObj2.printValues();

        //elements individual type casting
        JavaGenerics<String, String> genObj3 = new JavaGenerics<>("Generics!", "in Java");
        genObj3.printValues();
        genericMethod("Hello generic method!"); //Even if all above are of Type T, that doesn't mean genObj1 = genObj2 = genObj3.
        //They are still different types of T. They are different references.


        //Specialized generics
        SpecializedGenericTypes<Animal> animal = new SpecializedGenericTypes<>(new Animal());
        animal.printObject(); //prints Animal methods even if overridden
        SpecializedGenericTypes<Dog> dog = new SpecializedGenericTypes<>(new Dog());
        dog.printObject(); //print dog methods as it is subclass of animal

        //you can return type T object too
        SpecializedGenericTypes<Animal> obj = new SpecializedGenericTypes<>(new Animal());
        System.out.println("Returned object : " + obj.returnObject().toString());
    }

    public static <T> void genericMethod(T varY) {
        System.out.println("Value : " + varY);
    }
}
