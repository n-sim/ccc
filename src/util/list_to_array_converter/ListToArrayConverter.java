package util.list_to_array_converter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ListToArrayConverter {

    public static Integer[][] convertListOfStringsTo2DimIntArrayByDelimiter(List<String> lines, String delimiter) {
        return convertListOfStringsTo2DimArrayByDelimiter(lines, delimiter, Integer::parseInt, Integer.class);
    }

    public static Character[][] convertListOfStringsTo2DimCharArrayByDelimiter(List<String> lines, String delimiter) {
        return convertListOfStringsTo2DimArrayByDelimiter(lines, delimiter, s -> s.charAt(0), Character.class);
    }

    private static <T> T[][] convertListOfStringsTo2DimArrayByDelimiter(List<String> lines, String delimiter, Function<String, T> mapper, Class<T> clazz) {
        int rows = lines.size();
        int cols = lines.get(0).split(delimiter).length;
        T[][] array = (T[][]) java.lang.reflect.Array.newInstance(clazz, rows, cols);

        for (int row = 0; row < rows; row++) {
            var rowParts = Arrays.stream(lines.get(row).split(delimiter))
                    .filter(s -> !s.isEmpty()) //remove all empty strings that were produced by splitting (e.g. if delimiter was on first position)
                    .collect(Collectors.toList());
            for (int col = 0; col < rowParts.size(); col++) {
                array[row][col] = mapper.apply(rowParts.get(col));
            }
        }
        return array;
    }

    public static Character[][] convertListOfStringsTo2DimCharArray(List<String> lines) {
        return convertListOfStringsTo2DimCharArrayByDelimiter(lines, "");
    }

    public static <T> T[][] convertListOfListsTo2DimArray(List<List<T>> listOfList) {
        int rows = listOfList.size();
        int cols = listOfList.get(0).size();
        T[][] array = (T[][]) new Object[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                array[row][col] = listOfList.get(row).get(col);
            }
        }
        return array;
    }

    public static <T> T[] convertListToArray(List<T> list) {
        T[] array = (T[]) new Object[list.size()];

        for (int i = 0; i < list.size(); i++) {
            array[i] = list.get(i);
        }

        return array;
    }
}
