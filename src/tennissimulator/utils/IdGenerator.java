package tennissimulator.utils;

public class IdGenerator {

    public static int playersCounter = 0;

    public static int getId() {
        return ++playersCounter;
    }

}