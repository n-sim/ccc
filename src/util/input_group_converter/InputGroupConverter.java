package util.input_group_converter;

import util.list_to_array_converter.ListToArrayConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a utility class for converting input data into groups, where each group
 * consists of lines that are grouped together and separated by a delimiter. Examples for a group: Honeycomb, Islandmap
 */
public class InputGroupConverter {
    /**
     * Parses an array of lines, ignoring the specified number of leading lines,
     * and returns a list of groups. Each group represents lines that are grouped together
     * and separated by the specified group separator.
     *
     * @param lines            The array of lines to parse.
     * @param skipLines        The number of leading lines to ignore.
     * @param groupsSeparator  The delimiter that separates groups.
     * @return                 A list of groups, where each group represents lines that are grouped together.
     */
    public static List<List<String>> parseLinesToGroups(String[] lines, int skipLines, String groupsSeparator) {
        lines = Arrays.copyOfRange(lines, skipLines, lines.length);

        List<List<String>> maps = new ArrayList<>();
        List<String> currentMap = new ArrayList<>();
        for (var line : lines) {
            if (line.equals(groupsSeparator)) {
                maps.add(currentMap);
                currentMap = new ArrayList<>();
                continue;
            }
            currentMap.add(line);
        }
        if (!currentMap.isEmpty()) {
            maps.add(currentMap);
        }

        return maps;
    }

    /**
     * Converts a list of groups, where each group consists of lines, into a two-dimensional array of characters.
     * Each line within a group is split at the specified delimiter.
     * For example, a group-line "O;X;X" delimited by ";" will be converted to [O, X, X].
     *
     * @param groups     A list of groups, where each group is represented as a list of strings.
     * @param delimiter  The delimiter used to split each line within a group.
     * @return           A list of groups represented as two-dimensional arrays of characters,
     *                   where each group-line has been split by the delimiter.
     */
    public static List<Character[][]> convertGroupsTo2DimCharArraysByDelimiter(List<List<String>> groups, String delimiter) {
        List<Character[][]> groupsAsArrays = new ArrayList<>();

        for (var group : groups) {
            groupsAsArrays.add(ListToArrayConverter.convertListOfStringsTo2DimCharArrayByDelimiter(group, delimiter));
        }

        return groupsAsArrays;
    }


    public static List<Character[][]> convertGroupsTo2DimCharArrays(List<List<String>> groups) {
        return convertGroupsTo2DimCharArraysByDelimiter(groups, "");
    }

    /**
     * Converts a list of groups, where each group consists of lines, into a two-dimensional array of integers.
     * Each line within a group is split at the specified delimiter and converted to an integer.
     * For example, a group-line "1;23;3" delimited by ";" will be converted to [1, 23, 3].
     *
     * @param groups     A list of groups, where each group is represented as a list of strings.
     * @param delimiter  The delimiter used to split each line within a group.
     * @return           A list of groups represented as two-dimensional arrays of integers,
     *                   where each group-line has been split by the delimiter and converted to an integer.
     */
    public static List<Integer[][]> convertGroupsTo2DimIntArraysByDelimiter(List<List<String>> groups, String delimiter) {
        List<Integer[][]> groupsAsArrays = new ArrayList<>();

        for (var group : groups) {
            groupsAsArrays.add(ListToArrayConverter.convertListOfStringsTo2DimIntArrayByDelimiter(group, delimiter));
        }

        return groupsAsArrays;
    }
}
