import util.*;


public class Level4 implements Level {
    static String testInput = "";
    static boolean runInTestMode = true;

    public static void main(String[] args) throws Exception{
        Level4 level = new Level4();

        if(runInTestMode){
            MetaUtils.runTest(level, testInput);
        }else{
            MetaUtils.run(level);
        }
    }

    public void solve(String inputString) throws Exception {

    }

}
