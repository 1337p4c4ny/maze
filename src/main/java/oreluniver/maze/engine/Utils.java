package oreluniver.maze.engine;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Utils {

    public static String loadResource(String fileName) {
        String result = "";
        try {
            InputStream in = Utils.class.getResourceAsStream(fileName);
            Scanner scanner = new Scanner(in, "UTF-8");
            result = scanner.useDelimiter("\\A").next();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static List<String> readAllLines(String fileName) throws Exception {
        List<String> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(Utils.class.getClass().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
        }
        return list;
    }
}
