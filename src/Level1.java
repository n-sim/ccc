import util.*;


public class Level1 implements Level {
    static String testInput = "";
    static boolean runInTestMode = true;

    public static void main(String[] args) throws Exception{
        Level1 level = new Level1();

        if(runInTestMode){
            MetaUtils.runTest(level, testInput);
        }else{
            MetaUtils.run(level);
        }
    }

    public void solve(String inputString) throws Exception {

    }

}
