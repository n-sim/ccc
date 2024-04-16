package util.traverse;

public class TraverseUtils {
    public static final int[][] HORIZONTAL_VERTICAL_MOVES = new int[][] {{-1, 0}, {1,0}, {0, -1}, {0, 1}};
    public static final int[][] DIAGONAL_HORIZONTAL_VERTICAL_MOVES = new int[][] {{-1, -1},
            {-1, 0},
            {-1, 1},
            {0, -1},
            {0, 1},
            {1, -1},
            {1, 0},
            {1, 1}};

    public static <T> boolean isValidPosition(int row, int col, T[][] map) {
        return row >= 0 && row < map.length && col >= 0 && col < map[row].length;
    }

    public static boolean isValidPosition(int row, int col, int[][] map) {
        return row >= 0 && row < map.length && col >= 0 && col < map[row].length;
    }

    public static boolean isValidPosition(int row, int col, char[][] map) {
        return row >= 0 && row < map.length && col >= 0 && col < map[row].length;
    }
}
