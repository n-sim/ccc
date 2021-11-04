import util.*;


public class Level3 implements Level {
    static String testInput = "";
    static boolean runInTestMode = true;

    public static void main(String[] args) throws Exception{
        Level3 level = new Level3();

        if(runInTestMode){
            MetaUtils.runTest(level, testInput);
        }else{
            MetaUtils.run(level);
        }
    }

    public void solve(String inputString) throws Exception {

    }

}
