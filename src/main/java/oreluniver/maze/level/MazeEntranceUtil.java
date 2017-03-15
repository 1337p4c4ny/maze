package oreluniver.maze.level;

import java.util.List;

public class MazeEntranceUtil {

    private static boolean willOfRandom() {
        return true;
    }

    /**
     * Adds an entrance to the given maze and registers it in the list of entrances
     * @param m matrix where the entrance should be added into
     * @param entrances - list of entrances
     */
    public static void addEntrance(int [][] m, List<Pair<Integer, Integer>> entrances) {
        if (willOfRandom()) { // vertical
            if (willOfRandom())
                entrances.add(addLeftEntrance(m));
            else
                entrances.add(addRightEntrance(m));
        }
        else {
            if (willOfRandom())
                entrances.add(addTopEntrance(m));
            else
                entrances.add(addBottomEntrance(m));
        }
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
}
