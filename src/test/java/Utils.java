import java.util.Random;

public class Utils {

    public static String getRandomStr(){
        String res = "";
        for (int i = 0; i < 15; i++){
            res += (char)(new Random().nextInt(125 - 65) + 65);
        }
        return res;
    }
}
