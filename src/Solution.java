import util.Tuple;
import util.input_group_converter.InputGroupConverter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Solution {
    static int levelNumber = 1;
    static boolean runInTestMode = true;
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
        final String linesDelimiter = "\r\n";
        String[] lines = inputString.split(linesDelimiter);

//        Input Parsing of Maps:
//        =====================================================================================
//        var firstLine = parseFirstLine(lines[0]);
//        List<List<String>> groups = InputGroupConverter.parseLinesToGroups(lines, 2, "");
//
//        List<Character[][]> maps = InputGroupConverter.convertGroupsTo2DimCharArrays(groups);
//        for (var map : maps) {
//            System.out.println(Arrays.deepToString(map));
//        }
//        List<Character[][]> mapsByDelimiter = InputGroupConverter.convertGroupsTo2DimCharArraysByDelimiter(groups, "-");
//        for (var map : mapsByDelimiter) {
//            System.out.println(Arrays.deepToString(map));
//        }


        StringJoiner output = new StringJoiner("\n");
        return output.toString();
    }

    public static String parseFirstLine(String firstLine) {
        // TODO: Handle first line
        return firstLine;

//        return Integer.parseInt(firstLine);

//        List<Integer> numbers: firstLine splitted by " "
//        return Arrays
//                .stream(firstLine.split(" "))
//                .map(Integer::parseInt)
//                .collect(Collectors.toList());

//        List<String> stringParts: firstLine splitted by " "
//        return Arrays
//                .stream(firstLine.split(" "))
//                .collect(Collectors.toList());
    }

    public static Tuple<Integer, Integer> findFirstPositionOfCharacterInMap(Character[][] map, Character target) {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == target) {
                    return new Tuple<>(row, col);
                }
            }
        }
        throw new IllegalArgumentException("Target:'" + target + "' is not in map contained!");
    }
}