import util.*;


public class Level5 implements Level {
    static String testInput = "";
    static boolean runInTestMode = true;

    public static void main(String[] args) throws Exception{
        Level5 level = new Level5();

        if(runInTestMode){
            MetaUtils.runTest(level, testInput);
        }else{
            MetaUtils.run(level);
        }
    }

    public void solve(String inputString) throws Exception {

    }

}
