package Generics;

public class Animal {
    public void makeNoise() {
        System.out.println("Makes noises");
    }
}

class Dog extends Animal {
    @Override
    public void makeNoise() {
        System.out.println("Gwrrr");
    }
}
