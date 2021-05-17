package Source;

import java.util.Random;

public class Dice {
    private int dice;

    public Dice(){
        this.dice = throwfunction();
    }

    public int throwfunction(){
        Random generator = new Random();
        this.dice = generator.nextInt(6) + 1;
        return dice;
    }

    public int throwQuestionMarkCard(){
        Random generator = new Random();
        dice = generator.nextInt(16) + 1;
        return dice;
    }

    public int getDice(){
        return this.dice;
    }
}
