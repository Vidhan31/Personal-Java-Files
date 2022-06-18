package OOPS;

interface NaturalLanguages {
    public void Greetings();
}

class English implements NaturalLanguages {
    @Override
    public void Greetings() {
        System.out.println("Hello!");
    }
}

public class DataHiding {
    public static void main(String[] args) {

        English eng = new English();
        eng.Greetings();
    }
}
