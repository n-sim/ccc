package util;

import java.util.Optional;

public class NumberUtils {
    public static Optional<Integer> tryParseInteger(String string) {
        try {
            return Optional.of(Integer.parseInt(string));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }
    }
}
