package util.islandproblem;

import util.Tuple;
import util.traverse.TraverseUtils;

import java.util.ArrayList;
import java.util.List;

public class Island {
    private List<Tuple<Integer, Integer>> positions;

    public Island() {
        this.positions = new ArrayList<>();
    }

    public Island(List<Tuple<Integer, Integer>> positions) {
        this.positions = positions;
    }

    public void addPosition(Tuple<Integer, Integer> position) {
        this.positions.add(position);
    }

    public int size() {
        return positions.size();
    }

    public int getPerimeter() {
        int perimeter = 0;
        for (var position : positions) {
            for (var move : TraverseUtils.HORIZONTAL_VERTICAL_MOVES) {
                if (!this.positions.contains(new Tuple<>(position.getX() + move[0], position.getY() + move[1]))) {
                    perimeter++;
                }
            }
        }
        return perimeter;
    }

    /*
        Island borders are included, so the bounding box goes "through" the border-island
     */
    public void printBoundingBox() {
        int minRow = Integer.MAX_VALUE;
        int maxRow = Integer.MIN_VALUE;
        int minCol = Integer.MAX_VALUE;
        int maxCol = Integer.MIN_VALUE;

        for (var position : this.positions) {
            int row = position.getX();
            int col = position.getY();

            if (row < minRow) {
                minRow = row;
            } else if (row > maxRow) {
                maxRow = row;
            }
            if (col < minCol) {
                minCol = col;
            } else if (col > maxCol) {
                maxCol = col;
            }
        }

        System.out.print("TL: (" + minRow + ", " + minCol + ")");
        System.out.println(" -------------- TR: (" + minRow + ", " + maxCol + ")");
        System.out.println("|                           |");
        System.out.println("|                           |");
        System.out.println("|                           |");
        System.out.println("|                           |");
        System.out.println("|                           |");
        System.out.println("|                           |");
        System.out.print("BL: (" + maxRow + ", " + minCol + ")");
        System.out.println(" -------------- BR: (" + maxRow + ", " + maxCol + ")");
    }

    @Override
    public String toString() {
        return "Island{" +
                "positions=" + positions +
                '}';
    }
}
