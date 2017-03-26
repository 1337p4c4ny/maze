package oreluniver.maze.level;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Class - helper for adding entrances to maze in form of bitmap
 */
public class BitmapEntranceUtil {

    static boolean willOfRandom() {
        return ThreadLocalRandom.current().nextInt(0, 2) != 0;
    }

    /**
     * Adds an entrance to the given maze and registers it in the list of entrances
     * @param m matrix where the entrance should be added into
     * @param entrances - list of entrances
     */
    public static void addEntrance(int [][] m, List<MazePos> entrances) {
        if (m == null || m.length == 0 || m[0].length == 0)
            return;

        if (entrances.size() > 0) {
           int area = getFreeArea(entrances.get(0), m[0].length, m.length);
            /* Areas:
             * 1 2
             * 3 4 */
           switch (area) {
               case 1:  addToFourthArea(m); break;
               case 2:  addToThirdArea(m);  break;
               case 4:  addToSecondArea(m); break;
               case 8:  addToFirstArea(m);  break;
               default: addToFirstArea(m);  break;
           }


        } else {
            if (willOfRandom()) { // vertical
                if (willOfRandom()) {
                    entrances.add(addEntranceInColumn(m, 0, 0, m.length));
                }
                else {
                    entrances.add(addEntranceInColumn(m, m[0].length -1, 0, m.length));
                }
            }
            else { // horizontal
                if (willOfRandom()) {
                    entrances.add(addEntranceInRow(m, 0, 0, m[0].length));
                }
                else {
                    entrances.add(addEntranceInRow(m, m.length-1, 0, m[0].length));
                }
            }
        }
    }

    private static MazePos addEntranceInColumn(int [][] m, int column, int fromRow, int toRow) {
        int nearDif = column == 0 ? 1 : -1;
        if (fromRow == 0) {
            fromRow = 1;
        }
        if (toRow == m.length) {
            toRow--;
        }
        int i = fromRow;
        while (i < toRow) {
            if (   m[i]    [column] == 0
                && m[i - 1][column] == 0
                && m[i + 1][column] == 0
                && m[i][column + nearDif] != 0
                && willOfRandom())
            {
                m[i][column] = 1;
                return new MazePos(i, column);
            }
            i++;
        }
        i = fromRow;
        while (i < toRow) {
            if (m[i][column] == 0 && m[i][column + nearDif] != 0) {
                m[i][column] = 1;
                return new MazePos(i, column);
            }
            i++;
        }
        System.out.println(String.format("Error adding entrance to column %d, starting from row %d, ending by row %d", column, fromRow, toRow));
        return null;
    }

    private static MazePos addEntranceInRow(int [][] m, int row, int fromCol, int toCol) {
        int nearDif = row == 0 ? 1 : -1;
        if (fromCol == 0) {
            fromCol = 1;
        }
        if (toCol == m[0].length) {
            toCol--;
        }
        int i = fromCol;
        while (i < toCol) {
            if (   m[row][i] == 0
                && m[row][i - 1] == 0
                && m[row][i + 1] == 0
                && m[row + nearDif][i] != 0
                && willOfRandom())
            {
                m[row][i] = 1;
                return new MazePos(row, i);
            }
            i++;
        }
        i = fromCol;
        while (i < toCol) {
            if (m[row][i] == 0 && m[row + nearDif][i] != 0) {
                m[row][i] = 1;
                return new MazePos(row, i);
            }
            i++;
        }
        System.out.println(String.format("Error adding entrance to row %d, starting from column %d, ending by column %d", row, fromCol, toCol));
        return null;
    }

    private static MazePos addToFirstArea(int [][] m) {
        if (willOfRandom()) { // add to top row
            return addEntranceInRow(m, 0, 0, m[0].length / 2);
        }
        else { // add to left row
            return addEntranceInColumn(m, 0, 0, m.length / 2);
        }
    }


    private static MazePos addToSecondArea(int [][] m) {
        if (willOfRandom()) { // add to top col
            return addEntranceInRow(m, 0, m[0].length / 2, m[0].length);
        }
        else { // add to right col
            return addEntranceInColumn(m, m[0].length -1, 0, m.length / 2);
        }
    }

    private static MazePos addToThirdArea(int [][] m) {
        if (willOfRandom()) { // add to left third
            return addEntranceInColumn(m, 0, m.length / 2, m.length);
        }
        else { // add to bottom third
            return addEntranceInRow(m, m.length - 1, 0, m[0].length / 2);
        }
    }

    private static MazePos addToFourthArea(int [][] m) {
        if (willOfRandom()) {
            // add to bottom fourth
            return addEntranceInRow(m, m.length - 1, m[0].length / 2, m[0].length);
        }
        else {
            // add to right fourth
            return addEntranceInColumn(m, m[0].length - 1, m.length / 2, m.length);
        }
    }

    private static int getFreeArea(MazePos entrance, int w, int h) {
        if ( (entrance.row == 0 && entrance.col < w / 2)
            || (entrance.col == 0 && entrance.row < h / 2))
        { // row is ocupied, 4-th is free
            return 1;
        }

        if ((entrance.row == 0 && entrance.col >= w / 2)
            || (entrance.col == w - 1 && entrance.row < h / 2))
        { // col is occupied, third is free
            return 2;
        }

        if ((entrance.col == 0 && entrance.row >= h / 2)
            || (entrance.row == h - 1 && entrance.col < w / 2))
        { // third is occupied, col is free
            return 4;
        }

        // if ((entrance.row == h -1 && entrnacce.col >= w / 2)
        //     || (entrnacce.col == w -1 && entrnacce.row >= h / 2))
        // {
        //     return 8
        // }

        // 4-th is occupied, row is free
        return 8;
    }
}
