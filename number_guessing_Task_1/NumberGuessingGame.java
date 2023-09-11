package codsoft;

public class NumberGuessingGame{
    public static void main(String[] args) {
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;

        Game game = new Game(minRange, maxRange, maxAttempts);
        game.play();
    }
}

