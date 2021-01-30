package utility.aule2020_21;

import java.util.Random;

public class SingletonRandomizer {
    private static Random r = new Random(100);

    public static Random getSingleton() {
        return r;
    }
}
