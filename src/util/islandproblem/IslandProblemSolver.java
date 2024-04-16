package util.islandproblem;

import util.Tuple;
import util.traverse.TraverseUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static util.traverse.TraverseUtils.isValidPosition;


public class IslandProblemSolver {
    private int islandMarker;
    private int[][] allowedMoves;
    private int[][] map;

    /***
     * Configure the IslandProblem
     * @param islandMarker  Value of land (e.g. 1 is land and 0 is water)
     * @param allowedMoves  All allowed in an array where a move is defined by an int[] (e.g. top: {-1, 0}; use TraverseUtils.HORIZONTAL_VERTICAL_MOVES)
     * @param map           Map consisting of islandMarkers (e.g. 1) and water (e.g. 0)
     */
    public IslandProblemSolver(int islandMarker, int[][] allowedMoves, int[][] map) {
        this.islandMarker = islandMarker;
        this.allowedMoves = allowedMoves;
        this.map = map;
    }

    /***
     * Get a preconfigured 4-Way-traversal IslandProblem
     * @param islandMarker  Value of land (e.g. 1 is land and 0 is water)
     * @param map           Map consisting of islandMarkers (e.g. 1) and water (e.g. 0)
     * @return IslandProblemsolver with basic 4-Way-traversal (top, right, bottom, left)
     */
    public static IslandProblemSolver buildSolverWithOnlyHorizontalVerticalMoves(int islandMarker, int[][] map) {
        return new IslandProblemSolver(islandMarker, TraverseUtils.HORIZONTAL_VERTICAL_MOVES, map);
    }

    /***
     * Get a preconfigured 8-Way-traversal IslandProblem
     * @param islandMarker  Value of land (e.g. 1 is land and 0 is water)
     * @param map           Map consisting of islandMarkers (e.g. 1) and water (e.g. 0)
     * @return IslandProblemsolver with basic 8-Way-traversal (diagonals + top, right, bottom, left)
     */
    public static IslandProblemSolver buildSolverWithDiagonalHorizontalVerticalMoves(int islandMarker, int[][] map) {
        return new IslandProblemSolver(islandMarker, TraverseUtils.DIAGONAL_HORIZONTAL_VERTICAL_MOVES, map);
    }

    public void setMap(int[][] map) {
        this.map = map;
    }

    public void setIslandMarker(int islandMarker) {
        this.islandMarker = islandMarker;
    }

    public void setAllowedMoves(int[][] allowedMoves) {
        this.allowedMoves = allowedMoves;
    }

    public int getIslandMarker() {
        return islandMarker;
    }

    public int[][] getAllowedMoves() {
        return allowedMoves;
    }

    public int[][] getMap() {
        return map;
    }

    private int traverseIsland(int row, int col, boolean[][] visited) {
        if (map[row][col] != islandMarker)
            return 0;

        visited[row][col] = true;
        int islandSize = 1;
        for (var move : allowedMoves) {
            if (isValidPosition(row + move[0], col + move[1], map) && !visited[row + move[0]][col + move[1]]) {
                islandSize += traverseIsland(row + move[0], col + move[1], visited);
            }
        }

        return islandSize;
    }

    private void addPositionsToIslandFromPosition(Island island, int row, int col, boolean[][] visited) {
        if (map[row][col] != islandMarker)
            return;
        visited[row][col] = true;
        island.addPosition(new Tuple<>(row, col));
        for (var move : allowedMoves) {
            if (isValidPosition(row + move[0], col + move[1], map) && !visited[row + move[0]][col + move[1]]) {
                addPositionsToIslandFromPosition(island, row + move[0], col + move[1], visited);
            }
        }
    }

    /***
     * Extracts all Islands of the map, where an Island contains all adjusted-positions of this.islandMarker
     * @return a List of Islands
     */
    public List<Island> extractAllIslands() {
        boolean[][] visited = new boolean[map.length][map[0].length];
        List<Island> islands = new ArrayList<>();

        int count = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == islandMarker && !visited[row][col]) {
                    Island island = new Island();
                    addPositionsToIslandFromPosition(island, row, col, visited);
                    islands.add(island);
                }
            }
        }
        return islands;
    }

    /***
     * Get the amount of different (not adjusted by allowed moves) islands
     * @return the amount of different Islands
     */
    public int getAmountOfIslands() {
        boolean[][] visited = new boolean[map.length][map[0].length];

        int count = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == islandMarker && !visited[row][col]) {
                    if (traverseIsland(row, col, visited) != 0) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    /***
     * Get the size of biggest island (=not-adjusted-islandMarks via allowedMoves)
     * @return Get the size of biggest island
     */
    public int getSizeOfBiggestIsland() {
        boolean[][] visited = new boolean[map.length][map[0].length];

        int maxSize = 0;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == islandMarker && !visited[row][col]) {
                    int size = traverseIsland(row, col, visited);
                    if (size != 0) {
                        maxSize = Math.max(maxSize, size);
                    }
                }
            }
        }

        return maxSize;
    }

    /***
     * Get the size of smallest island (=not-adjusted-islandMarks via allowedMoves)
     * @return Get the size of smallest island
     */
    public int getSizeOfSmallestIsland() {
        boolean[][] visited = new boolean[map.length][map[0].length];

        int minSize = Integer.MAX_VALUE;
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == islandMarker && !visited[row][col]) {
                    int size = traverseIsland(row, col, visited);
                    if (size != 0) {
                        minSize = Math.min(minSize, size);
                    }
                }
            }
        }

        return minSize;
    }

    /***
     * Prints the map in a readable way (line by line)
     */
    public void printMap() {
        for (int[] chars : map) {
            for (int aChar : chars) {
                System.out.print(aChar);
            }
            System.out.println();
        }
    }

    /***
     * Prints all configs:
     * - islandMarker
     * - allowed Moves
     * - readably Map
     */
    public void printMapInfo() {
        System.out.println("=== MAP INFO START===");
        System.out.println("Islandmarker: " + islandMarker);
        System.out.println("Allowed Moves: " + Arrays.deepToString(allowedMoves));
        System.out.println("Map:");
        printMap();
        System.out.println("=== MAP INFO END ===");
    }

    public static int[][] convertCharMapToIntMap(char[][] map) {
        int[][] converted = new int[map.length][map[0].length];

        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                converted[row][col] = map[row][col];
            }
        }

        return converted;
    }
}
