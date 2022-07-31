package FactoryPattern;

public class GreetingsFactory {

    public Greetings getObjectOf(String language) {

        if (language == null || language.isEmpty()) {
            return null;
        }

        return switch (language) {
            case "Hindi", "HINDI", "hindi" -> new Hindi();
            case "Marathi", "MARATHI", "marathi" -> new Marathi();
            case "English", "ENGLISH", "english" -> new English();
            default -> throw new IllegalArgumentException("Illegal input " + language);
        };
    }
}
