package oreluniver.maze.engine;

import java.io.File;
import java.io.InputStream;
import java.util.Scanner;

public class Utils {

    public static String loadResource(String fileName) {
        String result = "";
        try {
            InputStream in = Utils.class.getClassLoader().getResourceAsStream(fileName);
            Scanner scanner = new Scanner(in, "UTF-8");
            result = scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
