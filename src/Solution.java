import util.Tuple;
import util.traverse.TraverseUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static int levelNumber = 4;
    static boolean runInTestMode = false;
    static int numberOfInputs = 5;


    public static void main(String[] args) throws Exception {
        if (runInTestMode) {
            String input = Files.readString(Path.of("level" + levelNumber + "_example.in"));
            String solution = solve(input);
            Files.writeString(Path.of("level" + levelNumber + "_example.out"), solution);
        } else {
            for (int i = 1; i <= numberOfInputs; i++) {
                String input = Files.readString(Path.of("level" + levelNumber + "_" + i + ".in"));
                String solution = solve(input);
                Files.writeString(Path.of("level" + levelNumber + "_" + i + ".out"), solution);
            }
        }
    }

    public static String solve(String inputString) throws Exception {
        StringJoiner output = new StringJoiner("\n");

        return output.toString();
    }
}