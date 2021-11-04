package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ParseUtils {
    public static List<Integer> getIntListSpaceSeparated(String input) {
        return Arrays.stream(input.split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
    }
}
