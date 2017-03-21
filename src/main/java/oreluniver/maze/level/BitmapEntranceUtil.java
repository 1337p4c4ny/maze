package oreluniver.maze.level;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;


/**
 * Class - helper for adding entrances to maze
 */
public class MazeEntranceUtil {

    private static boolean willOfRandom() {
        return ThreadLocalRandom.current().nextInt(0, 2) != 0;
    }

    /**
     * Adds an entrance to the given maze and registers it in the list of entrances
     * @param m matrix where the entrance should be added into
     * @param entrances - list of entrances
     */
    public static void addEntrance(int [][] m, List<Pair<Integer, Integer>> entrances) {
        if (entrances.size() > 0) {
           int area = getFreeArea(entrances.get(0), m[0].length, m.length);

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
                    entrances.add(addLeftEntrance(m));
                }
                else {
                    entrances.add(addRightEntrance(m));
                }
            }
            else { // horizontal
                if (willOfRandom()) {
                    entrances.add(addTopEntrance(m));
                }
                else {
                    entrances.add(addBottomEntrance(m));
                }
            }
        }
    }

    private static Pair<Integer, Integer> addEntranceInColumn(int [][] m, int coulumn) {
        return null;
    }

    private static Pair<Integer, Integer> addEntranceInRow(int [][] m, int row) {
        return null;
    }

    private static Pair<Integer, Integer> addTopEntrance(int [][] m) {
        int i = 1;
        while (i < m[0].length - 1) {
            if (   m[0][i] == 0
                    && m[0][i - 1] == 0
                    && m[0][i + 1] == 0
                    && m[1][i] != 0
                    && willOfRandom()
                    )
            {
                m[0][i] = 1;
                return new Pair<>(0, i);
            }
            i++;
        }
        return null;
    }

    private static Pair<Integer, Integer> addLeftEntrance(int [][] m) {
        int i = 1;
        while (i < m.length - 1) {
            if (   m[i][0] == 0
                    && m[i - 1][0] == 0
                    && m[i + 1][0] == 0
                    && m[i][1] != 0
                    && willOfRandom()
                    )
            {
                m[i][0] = 1;
                return new Pair<>(i, 0);
            }
            i++;
        }
        return null;
    }

    private static Pair<Integer, Integer> addRightEntrance(int [][] m) {
        int i = 1;
        while (i < m.length - 1) {
            if (   m[i][m.length - 1] == 0
                    && m[i - 1][m[i].length - 1] == 0
                    && m[i + 1][m[i].length - 1] == 0
                    && m[i][m[i].length - 2] != 0
                    && willOfRandom()
                    )
            {
                m[i][m[i].length - 1] = 1;
                return new Pair<>(i, m[i].length - 1);
            }
            i++;
        }
        return null;
    }

    private static Pair<Integer, Integer> addBottomEntrance(int [][] m) {
        int i = 1;
        while (i < m[m.length - 1].length - 1) {
            if (   m[m.length - 1][i] == 0
                    && m[m.length - 1][i - 1] == 0
                    && m[m.length - 1][i + 1] == 0
                    && m[m.length - 2][i] != 0
                    && willOfRandom()
                    )
            {
                m[m.length - 1][i] = 1;
                return new Pair<>(m.length - 1, i);
            }
            i++;
        }
        return null;
    }

    private static Pair<Integer, Integer> addToFirstArea(int [][] m) {
        if (willOfRandom()) {
            // add to top first
            return null;
        }
        else {
            // add to left first
            return null;
        }
    }


    private static Pair<Integer, Integer> addToSecondArea(int [][] m) {
        if (willOfRandom()) {
            // add to top second
            return null;
        }
        else {
            // add to right second
            return null;
        }
    }

    private static Pair<Integer, Integer> addToThirdArea(int [][] m) {
        if (willOfRandom()) {
            // add to left third
            return null;
        }
        else {
            // add to bottom third
            return null;
        }
    }

    private static Pair<Integer, Integer> addToFourthArea(int [][] m) {
        if (willOfRandom()) {
            // add to bottom fourth
            return null;
        }
        else {
            // add to right fourth
            return null;
        }
    }

    private static int getFreeArea(Pair<Integer, Integer> entrance, int w, int h) {
        if ( (entrance.first == 0 && entrance.second < w / 2)
            || (entrance.second == 0 && entrance.first < h / 2))
        { // first is ocupied, 4-th is free
            return 1;
        }

        if ((entrance.first == 0 && entrance.second >= w / 2)
            || (entrance.second == w - 1 && entrance.first < h / 2))
        { // second is occupied, third is free
            return 2;
        }

        if ((entrance.second == 0 && entrance.first >= h / 2)
            || (entrance.first == h - 1 && entrance.second < w / 2))
        { // third is occupied, second is free
            return 4;
        }

        // if ((entrance.first == h -1 && entrnacce.second >= w / 2)
        //     || (entrnacce.second == w -1 && entrnacce.first >= h / 2))
        // {
        //     return 8
        // }

        // 4-th is occupied, first is free
        return 8;
    }
}
