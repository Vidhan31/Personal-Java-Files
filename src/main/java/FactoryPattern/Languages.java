package FactoryPattern;

interface Greetings {

    void greet();
}

public class Languages {

    public static void main(String[] args) {

        GreetingsFactory greetingsFactory = new GreetingsFactory();
        Greetings greetings = greetingsFactory.getObjectOf("English");
        greetings.greet();
    }
}
