package util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MetaUtils {
    public static List<String> getAllFilepathsInFolder(String levelName) {
        List<String> filenames = new ArrayList<>();
        File folder = new File("inputs/" + levelName);
        File[] listOfFiles = folder.listFiles();

        for (File file : listOfFiles) {
            filenames.add(file.getAbsolutePath());
        }

        return filenames;
    }

    public static void run(Level level){
        String levelName = level.getClass().getSimpleName().toLowerCase();
        List<String> inputFileNames = MetaUtils.getAllFilepathsInFolder(levelName);
        for (String inputFileName : inputFileNames) {
            try{
                System.out.println(String.format("======= %s =======", inputFileName));
                String input = InputUtils.getFirstLineAsString(inputFileName);
                level.solve(input);
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public static void runTest(Level level, String testInput) throws Exception {
        System.out.println("======= TEST =======");
        level.solve(testInput);
        System.out.println();
    }
}
