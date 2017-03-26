package oreluniver.maze.level;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class MazeBuilder {

    public static boolean willOfRandom() {
        return ThreadLocalRandom.current().nextInt(0, 2) != 0;
    }

    public static int countElem(int [] row, int elem) {
        int count = 0;
        for (int i = 0; i < row.length; ++i) {
            if (row[i] == elem) {
                count++;
            }
        }
        return count;
    }

    public static int [][] makeBitmap(int width, int height) {
        int [][] m = new int[height][width];

        for (int i = 1; i < width - 1; ++i) {// fill row line with 111111
            m[1][i] = 1;
        }

        int fresh = 1;
        int curRow = 1;
        int j = 0;

        // row row
        int i = 2;
        while (i < width - 2) {
            if (willOfRandom()) { // add a wall
                m[curRow][i] = 0;
                j =  i - 1;
                while (m[curRow][j] != 0) { // unite area to left
                    j--;
                }
                if (j < i - 1) {
                    j++;
                    for (int k = j; k < i; ++k){
                        m[curRow][k] = m[curRow][j];
                    }
                }
                fresh++;
                j = i + 1;
                while (m[curRow][j] != 0) { // unite area to right
                    m[curRow][j] = fresh;
                    j++;
                }
                i += 2;
            }
            else {
                i++;
            }
        }

        fresh++;
        curRow++;

        // general case
        while (curRow < height - 2) {
            for (int k = 1; k < width - 1; ++k) { // copy prev row
                m[curRow][k] = m[curRow - 1][k];
            }

            i = 1;
            while (i < width - 1) {
                if (m[curRow][i] == 0 && (m[curRow][i - 1] == 0 || willOfRandom())) {
                    m[curRow][i] = fresh;
                    fresh++;
                }
                i++;
            }
            i = 1;
            while (i < width - 1) {
                if (m[curRow][i] != 0 && willOfRandom()) {
                    addWall(m[curRow], i);
                }
                i++;
            }
            uniteAreas(m[curRow]);
            curRow++;
        }

            // last row
        for (int k = 1; k < width - 1; ++k) { // copy prev row
            m[curRow][k] = m[curRow - 1][k];
        }

        i = 1;
        while (i < width - 1) { // wipe all walls
            if (m[curRow][i] == 0) {
                m[curRow][i] = fresh;
                fresh++;
            }
            i++;
        }

        i = 1;
        while (i < width - 1) {
            if (m[curRow][i] != 0 && willOfRandom()) {
                addWall(m[curRow], i);
            }
            i++;
        }
        return m;
    }

    private static void uniteAreas(int [] row) {
        for (int i = 1; i < row.length - 1; ++i) {
            if (row[i] != 0 && row[i - 1] != 0) {
                row[i] = row[i - 1];
            }
        }
    }

    private static void addWall(int [] row, int i) {
        int count = countElem(row, row[i]);

        if (    (  row[i] == row[i - 1]
                && row[i] == row[i + 1]
                )
            ||
                (  row[i - 1] == 0
                && count > 1
                )
            ||
                (  row[i + 1] == 0
                && count > 1
                )
            )
        {
            row[i] = 0;
        }
    }


    public static Maze build(int width, int height, int countOfEntrances) {
        List<MazePos> entrances = new ArrayList<>();
        int [][] bitmap = makeBitmap(width, height);
        for (int i = 0; i < countOfEntrances; ++i) {
            BitmapEntranceUtil.addEntrance(bitmap, entrances);
        }
        return new Maze(bitmap, entrances);
    }


    public static void printBitmap(int [][] m) {
        for (int i = 0; i < m.length; ++i) {
            for (int j = 0; j < m[i].length; ++j) {
                System.out.print(m[i][j] == 0 ? "# " :  "  ");
            }
            System.out.println();
        }
    }

    public static  void printMaze(Maze maze) {
        int w = maze.getWidth();
        int h = maze.getHeight();
        List<MazePos> entrances =  maze.getEntrances();
        for (MazePos e : entrances) {
            System.out.format("Entrance <%d, %d>\n", e.row, e.col);
        }
       printBitmap(maze.bitmap);
    }

    public static void main(String ... args) {
        int w = 50;
        int h = 50;
        int entranceCount = 2;

        if (args.length >= 2) {
            w = Integer.parseInt(args[0]);
            h = Integer.parseInt(args[1]);
        }

        printMaze(build(w, h, entranceCount));
    }
}
