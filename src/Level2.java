import util.*;


public class Level2 implements Level {
    static String testInput = "";
    static boolean runInTestMode = true;

    public static void main(String[] args) throws Exception{
        Level2 level = new Level2();

        if(runInTestMode){
            MetaUtils.runTest(level, testInput);
        }else{
            MetaUtils.run(level);
        }
    }

    public void solve(String inputString) throws Exception {

    }

}
