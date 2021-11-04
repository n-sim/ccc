package util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputUtils {
    public static String getFirstLineAsString(String filename) throws Exception{
        return Files.readAllLines(Path.of(filename)).get(0);
    }

    public static List<Integer> getIntListSpaceSeparated(String filename) throws Exception {
        return ParseUtils.getIntListSpaceSeparated(Files.readAllLines(Path.of(filename)).get(0));
    }

    public static Set<Integer> getIntSetSpaceSeparated(String filename) {
        try (Stream<String> lines = Files.lines(Path.of(filename))) {
            return Arrays.stream(lines.findFirst().get().split(" ")).mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());
        } catch (IOException e) {
            return null;
        }
    }

    public static Set<Integer> getIntSetLineSeparated(String filename) {
        try (Stream<String> lines = Files.lines(Path.of(filename))) {
            return lines.mapToInt(Integer::parseInt).boxed().collect(Collectors.toSet());
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Integer> getIntListLineSeparated(String filename){
        try (Stream<String> lines = Files.lines(Path.of(filename))) {
            return lines.mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        } catch (IOException e) {
            return null;
        }
    }

    public static List<Long> getLongListLineSeparated(String filename){
        try (Stream<String> lines = Files.lines(Path.of(filename))) {
            return lines.mapToLong(Long::parseLong).boxed().collect(Collectors.toList());
        } catch (IOException e) {
            return null;
        }
    }

    public static List<String> getStringListLineSeparated(String filename){
        try (Stream<String> lines = Files.lines(Path.of(filename))) {
            return lines.collect(Collectors.toList());
        } catch (IOException ignored) {
            return null;
        }
    }
}
