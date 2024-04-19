import util.Tuple;
import util.input_group_converter.InputGroupConverter;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Solution {
    static int levelNumber = 2;
    static boolean runInTestMode = false;
    static int numberOfInputs = 5;

    record Instance(int lawnWidth, int lawnHeight, char[][] lawn, String instructions) {
    }


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
        final String linesDelimiter = "\r\n";
        String[] lines = inputString.split(linesDelimiter);
//        Input Parsing of Maps:
//        =====================================================================================
        var firstLine = parseFirstLine(lines[0]);
        List<List<String>> groups = InputGroupConverter.parseLinesToGroups(lines, 1, "");
        List<String> paths = groups.get(0);
        
        for (var path : paths) {
            output.add(countOcc(path));
        }

//        List<Character[][]> maps = InputGroupConverter.convertGroupsTo2DimCharArrays(groups);
//        for (var map : maps) {
//            System.out.println(Arrays.deepToString(map));
//        }
//        List<Character[][]> mapsByDelimiter = InputGroupConverter.convertGroupsTo2DimCharArraysByDelimiter(groups, "-");
//        for (var map : mapsByDelimiter) {
//            System.out.println(Arrays.deepToString(map));
//        }


        return output.toString();
    }

    public static String countOcc(String instance) {
        List<Tuple<Integer, Integer>> pathPositions = new LinkedList<>();
            Tuple<Integer, Integer> currentPosition = new Tuple<>(0, 0);
        pathPositions.add(currentPosition);

        // X: nach open
        for (char c : instance.toCharArray()) {
            switch (c) {
                case 'W': currentPosition = new Tuple<>(currentPosition.getX()-1, currentPosition.getY()); pathPositions.add(currentPosition);  break;
                case 'S': currentPosition = new Tuple<>(currentPosition.getX()+1, currentPosition.getY()); pathPositions.add(currentPosition); break;
                case 'D': currentPosition = new Tuple<>(currentPosition.getX(), currentPosition.getY() +1); pathPositions.add(currentPosition); break;
                case 'A': currentPosition = new Tuple<>(currentPosition.getX(), currentPosition.getY()-1); pathPositions.add(currentPosition); break;
            }
        }
        /*
        pathPositions.stream().mapToInt(Tuple::getX).max();
        pathPositions.stream().mapToInt(Tuple::getY).max();
*/
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        for (var position : pathPositions) {
            int row = position.getX();
            int col = position.getY();

            if (row < minRow) {
                minRow = row;
            } else if (row > maxRow) {
                maxRow = row;
            }
            if (col < minCol) {
                minCol = col;
            } else if (col > maxCol) {
                maxCol = col;
            }
        }
        int width = Math.abs(minCol) + Math.abs(maxCol) + 1;
        int height = Math.abs(minRow) + Math.abs(maxRow) + 1;

        return String.valueOf(width) + " " + String.valueOf(height);


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