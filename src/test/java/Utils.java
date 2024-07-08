import java.util.Random;

public class Utils {

    public static String getRandomStr(){
        String res = "";
        for (int i = 0; i < 15; i++){
            res += (char)(new Random().nextInt(26) + 97);
        }
        return res;
    }

    public static int randInt(){
        return new Random().nextInt(10);
    }

    public static boolean getRandomBool(){
        return new Random().nextBoolean();
    }
}
