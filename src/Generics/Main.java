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

}

public class Main {

    public static void main(String[] args) {

        //argument passed to the type parameter must be a reference type. primitive arrays are allowed
        // as they themselves are reference types.
        JavaGenerics<Integer> genObj1 = new JavaGenerics<>(34);
        genObj1.printValues();

        //Type safety: Specifying the type beforehand helps us locate the problem at compile time rather than runtime.
        //here <T> or <Double> is a bound.
        JavaGenerics<Double> genObj2 = new JavaGenerics<>(33.43223423);
        genObj2.printValues();

        //elements individual type casting
        JavaGenerics<String> genObj3 = new JavaGenerics<>("Generics!");
        genObj3.printValues();
        genericMethod("Hello generic method!"); //Even if all above are of Type T, that doesn't mean genObj1 = genObj2 = genObj3.
        //They are still different types of T. They are different references.


        //Specialized generics
        SpecializedGenericTypes<Animal> animal = new SpecializedGenericTypes<>(new Animal());
        animal.printObject(); //prints Animal methods even if overridden
        SpecializedGenericTypes<Dog> dog = new SpecializedGenericTypes<>(new Dog());
        dog.printObject(); //print dog methods as it is subclass of animal
    }

    public static <T> void genericMethod(T varY) {
        System.out.println("Value : " + varY);
    }
}
