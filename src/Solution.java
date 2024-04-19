import util.Tuple;
import util.input_group_converter.InputGroupConverter;
import util.list_to_array_converter.ListToArrayConverter;
import util.traverse.TraverseUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    static int levelNumber = 3;
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
        List<String> linesWithoutFirstLine = groups.get(0);

        List<Instance> instances = parseInputToInstances(linesWithoutFirstLine);
        for (Instance instance : instances) {
            output.add(countOcc(instance));
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

    public static List<Instance> parseInputToInstances(List<String> linesWitoutTopLine) {
        List<Instance> instances = new ArrayList<>();

        for (int lineIndex = 0; lineIndex < linesWitoutTopLine.size(); lineIndex++) {
            List<Integer> widthAndHeight = parseFirstLine(linesWitoutTopLine.get(lineIndex));
            int width = widthAndHeight.get(0);
            int height = widthAndHeight.get(1);
            char[][] lawn = new char[height][width];
            lineIndex++; //Skip line with height-width-data
            for (int i = 0; i < height; i++, lineIndex++) {
                for (int j = 0; j < linesWitoutTopLine.get(lineIndex).length(); j++) {
                    lawn[i][j] = linesWitoutTopLine.get(lineIndex).charAt(j);
                }
            }
            String lineInstructions = linesWitoutTopLine.get(lineIndex); // wegen forloop lineIndex zum schlus nochmal incrementiert, deshalb davor niht mehr notwendig
            instances.add(new Instance(width, height, lawn, lineInstructions));
        }

        return instances;
    }

    public static String countOcc(Instance instance) {
        Tuple<Integer, Integer> offset = calcOffset(instance.instructions);
        List<Tuple<Integer, Integer>> pathPositions = new LinkedList<>();
        Tuple<Integer, Integer> currentPosition = new Tuple<>(offset.getX(), offset.getY());
        pathPositions.add(currentPosition);

        // X: nach open
        for (char c : instance.instructions.toCharArray()) {
            Tuple<Integer, Integer> newPosCandiate = null;
            if (c == 'W') {
                newPosCandiate = new Tuple<>(currentPosition.getX() - 1, currentPosition.getY());
            } else if (c == 'S') {
                newPosCandiate = new Tuple<>(currentPosition.getX() + 1, currentPosition.getY());
            } else if (c == 'D') {
                newPosCandiate = new Tuple<>(currentPosition.getX(), currentPosition.getY() + 1);
            } else if (c == 'A') {
                newPosCandiate = new Tuple<>(currentPosition.getX(), currentPosition.getY() - 1);
            } else {
                throw new IllegalArgumentException("fuck oida");
            }
            if (!pathPositions.contains(newPosCandiate) &&
                    TraverseUtils.isValidPosition(newPosCandiate.getX(), newPosCandiate.getY(),instance.lawn) &&
                    instance.lawn()[newPosCandiate.getX()][newPosCandiate.getY()] != 'X') {
                currentPosition = newPosCandiate;
                pathPositions.add(currentPosition);
            } else {
                return "INVALID";
            }
        }

        return pathPositions.size() + countOccOfCharacterInMap(instance.lawn, 'X') == (instance.lawnHeight * instance.lawnWidth) ?
                "VALID" : "INVALID";
    }

    public static Tuple<Integer, Integer> calcOffset(String instance) {
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

        return new Tuple<>(Math.abs(minRow), Math.abs(minCol));
    }


    public static List<Integer> parseFirstLine(String firstLine) {
        // TODO: Handle first line
//        return firstLine;

//        return Integer.parseInt(firstLine);

//        List<Integer> numbers: firstLine splitted by " "
        return Arrays
                .stream(firstLine.split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

//        List<String> stringParts: firstLine splitted by " "
//        return Arrays
//                .stream(firstLine.split(" "))
//                .collect(Collectors.toList());
    }

    public static int countOccOfCharacterInMap(char[][] map, char target) {
        int counter = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == target) {
                    counter++;
                }
            }
        }
        return counter;
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